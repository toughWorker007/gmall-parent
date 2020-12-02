package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.product.service.SpuService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @desc
 */
@RestController
@CrossOrigin
@RequestMapping("/admin/product/")
public class SpuApiController {

    @Autowired
    SpuService spuService;

    @GetMapping("{pageNum}/{limit}")
    public Result spuPage(@PathVariable("pageNum") Long pageNum
            , @PathVariable("limit") Long limit, Long category3Id){
        IPage<SpuInfo> page = new Page<>();
        page.setSize(limit);
        page.setCurrent(pageNum);

        IPage<SpuInfo> spuInfoIPage = spuService.spuInfoIPage(page, category3Id);
        return Result.ok(spuInfoIPage);
    }

    @PostMapping("saveSpuInfo")
    public Result saveSpuInfo(@RequestBody SpuInfo spuInfo){
        spuService.saveSpuInfo(spuInfo);
        return Result.ok();
    }



}
