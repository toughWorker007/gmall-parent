package com.atguigu.gmall.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @desc
 */
@Aspect
@Component
public class GamllCacheAspect {

    @Autowired
    RedisTemplate redisTemplate;

    @Around("@annotation(com.atguigu.gmall.config.GmallCache)")
    public Object skuCacheAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){

        Object result;
        String cacheKey = null;

        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        String name = signature.getMethod().getName();

        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            cacheKey = name + ":" + arg;
        }

        result = redisTemplate.opsForValue().get(cacheKey);
        if (result == null){
            String lockValue = UUID.randomUUID().toString();
            String lockKey = cacheKey + "lock";
            Boolean ok = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, 5, TimeUnit.SECONDS);
            if (ok){
                try {
                    result = proceedingJoinPoint.proceed();
                    if (result != null) {
                        redisTemplate.opsForValue().set(cacheKey,result);
                    }else {
                        //同步空缓存(一般加上aliveTime,防止拿不到后续db的添加)，抵挡缓存穿透
                        redisTemplate.opsForValue().setIfAbsent(cacheKey,result,5,TimeUnit.SECONDS);
                    }

                    // 删除锁
                    String openKey = (String) redisTemplate.opsForValue().get(cacheKey+":lock");
                    if (lockValue.equals(openKey)) {
                        redisTemplate.delete(cacheKey +":lock");
                    }
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }else {
                //别人正在取值，自旋等别人取值结束，直接拿缓存。
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return redisTemplate.opsForValue().get(cacheKey);
            }

        }


        return result;
    }
}
