package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.SpuImage;
import com.atguigu.gmall.model.product.SpuSaleAttr;

import java.util.List;

/**
 * @desc
 */
public interface SkuService {
    List<SpuImage> spuImageList(Long spuId);

    List<SpuSaleAttr> spuSaleAttrList(Long spuId);
}
