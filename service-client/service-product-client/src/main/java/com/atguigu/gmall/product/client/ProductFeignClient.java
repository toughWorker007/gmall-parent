package com.atguigu.gmall.product.client;

import com.atguigu.gmall.model.product.BaseCategoryView;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @desc
 */
@FeignClient("service-product")
public interface ProductFeignClient {

    /**item:
     1.Price
     2.SkuInfo
     3.SpuSaleAttrList
     4.CategoryView
     */

    @RequestMapping("api/product/getPrice/{skuId}")
    BigDecimal getPrice(@PathVariable("skuId") Long skuId);

    @RequestMapping("api/product/getSkuInfoById/{skuId}")
    SkuInfo getSkuInfoById(@PathVariable("skuId") Long skuId);

    @RequestMapping("api/product/getSpuSaleAttrList/{spuId}/{skuId}")
    List<SpuSaleAttr> getSpuSaleAttrList(@PathVariable("spuId") Long spuId,@PathVariable("skuId") Long skuId);

    @RequestMapping("api/product/getCategoryView/{category3Id}")
    BaseCategoryView getCategoryView(@PathVariable("category3Id") Long category3Id);

    @RequestMapping("api/product/getSaleAttrValuesBySpu/{spuId}")
    Map<String,Long> getSaleAttrValuesBySpu(@PathVariable("spuId") Long spuId);
}
