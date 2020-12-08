package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.model.product.BaseCategoryView;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.product.service.CategoryService;
import com.atguigu.gmall.product.service.SkuService;
import com.atguigu.gmall.product.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @desc
 */
@RestController
@RequestMapping("api/product/")
public class ProductApiController {

    @Autowired
    SkuService skuService;

    @Autowired
    SpuService spuService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping("getPrice/{skuId}")
    BigDecimal getPrice(@PathVariable("skuId") Long skuId){

        BigDecimal bigDecimal = skuService.getPrice(skuId);
        return bigDecimal;
    }

    //enRedis
    @RequestMapping("getSkuInfoById/{skuId}")
    SkuInfo getSkuInfoById(@PathVariable("skuId") Long skuId){
        return skuService.getSkuInfoById(skuId);
    }


    @RequestMapping("getSpuSaleAttrList/{spuId}/{skuId}")
    List<SpuSaleAttr> getSpuSaleAttrList(@PathVariable("spuId") Long spuId, @PathVariable("skuId") Long skuId){
        List<SpuSaleAttr> spuSaleAttrs = spuService.getSpuSaleAttrListCheckBySku(spuId,skuId);
        return spuSaleAttrs;
    }

    @RequestMapping("getCategoryView/{category3Id}")
    BaseCategoryView getCategoryView(@PathVariable("category3Id") Long category3Id){
        return categoryService.getCategoryView(category3Id);
    }

    @RequestMapping("getSaleAttrValuesBySpu/{spuId}")
    Map<String,Long> getSaleAttrValuesBySpu(@PathVariable("spuId") Long spuId){
        Map<String,Long> map = spuService.getSaleAttrValuesBySpu(spuId);
        return map;
    }
}
