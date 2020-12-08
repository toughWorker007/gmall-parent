package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

/**
 * @desc
 */
public interface SpuService{
    IPage<SpuInfo> spuInfoIPage(IPage<SpuInfo> page, Long category3Id);

    void saveSpuInfo(SpuInfo spuInfo);

    List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(Long spuId, Long skuId);

    Map<String, Long> getSaleAttrValuesBySpu(Long spuId);
}
