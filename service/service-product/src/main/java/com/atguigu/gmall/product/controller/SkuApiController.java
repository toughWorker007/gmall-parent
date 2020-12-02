package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuImage;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.product.service.SkuService;
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
public class SkuApiController {

    @Autowired
    SkuService skuService;

//    @Autowired


    @GetMapping("spuImageList/{spuId}")
    public Result spuImageList(@PathVariable("spuId") Long spuId){
        List<SpuImage> spuImageList = skuService.spuImageList(spuId);
        return Result.ok(spuImageList);
    }

    @GetMapping("spuSaleAttrList/{spuId}")
    public Result spuSaleAttrList(@PathVariable("spuId") Long spuId){
        List<SpuSaleAttr> spuSaleAttrList = skuService.spuSaleAttrList(spuId);
        return Result.ok(spuSaleAttrList);
    }
    @PostMapping("saveSkuInfo")
    public Result saveSkuInfo(@RequestBody SkuInfo skuInfo){

        skuService.saveSkuInfo(skuInfo);

        return Result.ok();
    }

    @GetMapping("list/{pageNum}/{limit}")
    public Result spuPage2(@PathVariable("pageNum") Long pageNum
            , @PathVariable("limit") Long limit){
        IPage<SkuInfo> page = new Page<>();
        page.setSize(limit);
        page.setCurrent(pageNum);

        IPage<SkuInfo> skuInfoIPage = skuService.skuInfoIPage(page);
        return Result.ok(skuInfoIPage);
    }

    @RequestMapping("cancelSale/{skuId}")
    public Result cancelSale(@PathVariable("skuId") Long skuId){

        skuService.cancelSale(skuId);

        return Result.ok();
    }

    @RequestMapping("onSale/{skuId}")
    public Result onSale(@PathVariable("skuId") Long skuId){

        skuService.onSale(skuId);

        return Result.ok();
    }
}
