package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.model.product.SpuInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @desc
 */
public interface TrademarkService {
    List<BaseTrademark> getTrademarkList();

    IPage<BaseTrademark> baseTrademarkPage(IPage<BaseTrademark> page, Long category3Id);
}
