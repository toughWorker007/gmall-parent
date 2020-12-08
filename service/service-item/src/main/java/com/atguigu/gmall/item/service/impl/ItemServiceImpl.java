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

/**
 * @desc
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ProductFeignClient productFeignClient;
    @Override
    public Map<String, Object> getItem(Long skuId) {

        /**item:
                1.Price
                2.SkuInfo
                3.SpuSaleAttrList
                4.CategoryView
         */
        BigDecimal price = productFeignClient.getPrice(skuId);
        SkuInfo skuInfo = productFeignClient.getSkuInfoById(skuId);
        List<SpuSaleAttr> spuSaleAttrList = productFeignClient.getSpuSaleAttrList(skuInfo.getSpuId(),skuId);
        BaseCategoryView categoryView = productFeignClient.getCategoryView(skuInfo.getCategory3Id());
        // 根据spuId查询出来的sku和销售属性值id的对应关系hash表
        Map<String,Long> jsonMap = productFeignClient.getSaleAttrValuesBySpu(skuInfo.getSpuId());
        HashMap<String, Object> map = new HashMap<>();
        map.put("price",price);
        map.put("skuInfo",skuInfo);
        map.put("spuSaleAttrList",spuSaleAttrList);
        map.put("categoryView",categoryView);
        map.put("valuesSkuJson", JSON.toJSONString(jsonMap));
        return map;
    }
}
