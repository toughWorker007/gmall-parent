package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.model.product.BaseSaleAttr;
import com.atguigu.gmall.product.service.BaseAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @desc
 */
@RestController
@CrossOrigin
@RequestMapping("admin/product/")
public class BaseAttrApiController {

    @Autowired
    BaseAttrService baseAttrService;

    @GetMapping("attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result attrInfoList(@PathVariable("category3Id") Long category3Id){

        List<BaseAttrInfo> attrInfos = baseAttrService.attrInfoList(category3Id);
        return Result.ok(attrInfos);
    }

    @PostMapping("saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){

        baseAttrService.saveAttrInfo(baseAttrInfo);

        return Result.ok();
    }

    @RequestMapping("/getAttrValueList/{attrId}")
    public Result getAttrValueList(@PathVariable Long attrId){

        List<BaseAttrValue> baseAttrValues = baseAttrService.getAttrValueList(attrId);

        return Result.ok(baseAttrValues);
    }

    @GetMapping("baseSaleAttrList")
    public Result baseSaleAttrList(){
        List<BaseSaleAttr> baseSaleAttrList = baseAttrService.baseSaleAttrList();
        return Result.ok(baseSaleAttrList);
    }
}
