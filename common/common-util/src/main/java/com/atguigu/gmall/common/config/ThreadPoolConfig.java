package com.atguigu.gmall.common.config;/**
 * @Date 2020/12/8
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Auther oldWorker
 * @Date 2020/12/8
 */
@Configuration
public class ThreadPoolConfig {
    @Bean
    public ThreadPoolExecutor getThreadPoolExecutor(){
        return new ThreadPoolExecutor(10,20,30
                , TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(20));
    }
}
