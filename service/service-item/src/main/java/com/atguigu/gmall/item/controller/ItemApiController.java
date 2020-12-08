package com.atguigu.gmall.item.controller;

import com.atguigu.gmall.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @desc
 */
@RestController
@RequestMapping("api/product/")

public class ItemApiController {
    @Autowired
    ItemService itemService;

    @RequestMapping("getItem/{skuId}")
    Map<String,Object> getItem(@PathVariable("skuId") Long skuId){
        return itemService.getItem(skuId);
    }
}
