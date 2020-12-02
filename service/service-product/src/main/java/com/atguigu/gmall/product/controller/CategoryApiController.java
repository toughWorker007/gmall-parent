package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.common.result.ResultCodeEnum;
import com.atguigu.gmall.model.product.BaseCategory1;
import com.atguigu.gmall.model.product.BaseCategory2;
import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @desc
 */
@RestController
@CrossOrigin
@RequestMapping("admin/product/")
public class CategoryApiController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("getCategory1")
    public Result getCategory1(){
        List<BaseCategory1> baseCategory1s = categoryService.getCategory1();
        return Result.ok(baseCategory1s);
    }

    @GetMapping("getCategory2/{category1Id}")
    public Result getCategory2(@PathVariable("category1Id") Long category1Id){
        List<BaseCategory2> baseCategory2s = categoryService.getCategory2(category1Id);
        return Result.ok(baseCategory2s);
    }

    @GetMapping("getCategory3/{category2Id}")
    public Result getCategory3(@PathVariable("category2Id") Long category2Id){
        List<BaseCategory3> baseCategory3s = categoryService.getCategory3(category2Id);
        return Result.ok(baseCategory3s);
    }
}
