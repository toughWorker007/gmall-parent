package com.atguigu.gmall.item.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.gmall.item.service.ItemService;
import com.atguigu.gmall.model.product.BaseCategoryView;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.product.client.ProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @desc
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ProductFeignClient productFeignClient;

    @Autowired
    ThreadPoolExecutor threadPoolExecutor;

    @Override
    public Map<String, Object> getItem(Long skuId) {

        /**item:
                1.Price
                2.SkuInfo
                3.SpuSaleAttrList
                4.CategoryView
         */
        Map<String, Object> map = new HashMap<>();

        //multiThread

        CompletableFuture<SkuInfo> skuInfoCompletableFuture = CompletableFuture.supplyAsync(new Supplier<SkuInfo>() {
            @Override
            public SkuInfo get() {
                SkuInfo skuInfo = productFeignClient.getSkuInfoById(skuId);
                map.put("skuInfo",skuInfo);
                return skuInfo;
            }
        },threadPoolExecutor);

        CompletableFuture<Void> priceRunAsync = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                BigDecimal price = productFeignClient.getPrice(skuId);       //async
                map.put("price", price);
            }
        });
        CompletableFuture<Void> completableFutureSaleAttrs = skuInfoCompletableFuture.thenAcceptAsync(new Consumer<SkuInfo>() {
            @Override
            public void accept(SkuInfo skuInfo) {
                List<SpuSaleAttr> spuSaleAttrs = productFeignClient.getSpuSaleAttrList(skuInfo.getSpuId(), skuId);
                map.put("spuSaleAttrList", spuSaleAttrs);
            }
        },threadPoolExecutor);


        CompletableFuture<Void> completableFutureCategory = skuInfoCompletableFuture.thenAcceptAsync(new Consumer<SkuInfo>() {
            @Override
            public void accept(SkuInfo skuInfo) {
                BaseCategoryView baseCategoryView = productFeignClient.getCategoryView(skuInfo.getCategory3Id());
                map.put("categoryView", baseCategoryView);
            }
        },threadPoolExecutor);


        CompletableFuture<Void> completableFutureJsonMap = skuInfoCompletableFuture.thenAcceptAsync(new Consumer<SkuInfo>() {
            @Override
            public void accept(SkuInfo skuInfo) {
                // 根据spuId查询出来的sku和销售属性值id的对应关系hash表
                Map<String, Long> jsonMap = productFeignClient.getSaleAttrValuesBySpu(skuInfo.getSpuId());
                String json = JSON.toJSONString(jsonMap);
                //System.out.println(json);
                map.put("valuesSkuJson", json);
            }
        },threadPoolExecutor);

        CompletableFuture.allOf(skuInfoCompletableFuture,priceRunAsync,completableFutureSaleAttrs
                ,completableFutureCategory,completableFutureJsonMap).join();


        return map;

        //singleThread
        /**BigDecimal price = productFeignClient.getPrice(skuId);       //async
         *
         *
        SkuInfo skuInfo = productFeignClient.getSkuInfoById(skuId);     //supply

        List<SpuSaleAttr> spuSaleAttrList = productFeignClient.getSpuSaleAttrList(skuInfo.getSpuId(),skuId);
         //     accept

        BaseCategoryView categoryView = productFeignClient.getCategoryView(skuInfo.getCategory3Id());
         //accept

        // 根据spuId查询出来的sku和销售属性值id的对应关系hash表
        Map<String,Long> jsonMap = productFeignClient.getSaleAttrValuesBySpu(skuInfo.getSpuId());
         //accept


        HashMap<String, Object> map = new HashMap<>();
        map.put("price",price);
        map.put("skuInfo",skuInfo);
        map.put("spuSaleAttrList",spuSaleAttrList);
        map.put("categoryView",categoryView);
        map.put("valuesSkuJson", JSON.toJSONString(jsonMap));
        return map;
         */
    }
}
