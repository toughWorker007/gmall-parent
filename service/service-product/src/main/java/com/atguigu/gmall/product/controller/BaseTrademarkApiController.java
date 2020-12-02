package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.product.service.TrademarkService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @desc
 */
@RestController
@CrossOrigin
@RequestMapping("/admin/product/")
public class BaseTrademarkApiController {

    @Autowired
    TrademarkService trademarkService;
    @GetMapping("baseTrademark/getTrademarkList")
    public Result getTrademarkList(){
        List<BaseTrademark> baseTrademarks = trademarkService.getTrademarkList();
        return Result.ok(baseTrademarks);
    }

    @GetMapping("baseTrademark/{pageNum}/{limit}")
    public Result baseTrademarkPage(@PathVariable("pageNum") Long pageNum
            , @PathVariable("limit") Long limit, Long category3Id){
        IPage<BaseTrademark> page = new Page<>();
        page.setSize(limit);
        page.setCurrent(pageNum);

        IPage<BaseTrademark> baseTrademarkPage = trademarkService.baseTrademarkPage(page, category3Id);
        return Result.ok(baseTrademarkPage);
    }}
