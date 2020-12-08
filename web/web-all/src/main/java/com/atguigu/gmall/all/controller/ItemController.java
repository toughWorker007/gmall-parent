package com.atguigu.gmall.all.controller;

import com.atguigu.gmall.item.client.ItemFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @desc
 */
@Controller
public class ItemController {

    @Autowired
    ItemFeignClient itemFeignClient;

    @GetMapping("/")
    public String index(){
        System.out.println("index");
        return "index";
    }

    @RequestMapping("{skuId}.html")
    public String item(@PathVariable("skuId") Long skuId, Model model){
        Map<String, Object> map = itemFeignClient.getItem(skuId);
        model.addAllAttributes(map);
        return "item/index";
    }
}
