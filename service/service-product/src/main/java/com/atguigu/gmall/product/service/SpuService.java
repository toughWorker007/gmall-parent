package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.SpuInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @desc
 */
public interface SpuService{
    IPage<SpuInfo> spuInfoIPage(IPage<SpuInfo> page, Long category3Id);

    void saveSpuInfo(SpuInfo spuInfo);
}
