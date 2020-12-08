package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseCategory1;
import com.atguigu.gmall.model.product.BaseCategory2;
import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.model.product.BaseCategoryView;
import com.atguigu.gmall.product.mapper.BaseCategoryViewMapper;
import com.atguigu.gmall.product.mapper.Category1Mapper;
import com.atguigu.gmall.product.mapper.Category2Mapper;
import com.atguigu.gmall.product.mapper.Category3Mapper;
import com.atguigu.gmall.product.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @desc
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private Category1Mapper category1Mapper;
    @Autowired
    private Category2Mapper category2Mapper;
    @Autowired
    private Category3Mapper category3Mapper;

    @Autowired
    BaseCategoryViewMapper baseCategoryViewMapper;

    @Override
    public List<BaseCategory1> getCategory1() {
        return category1Mapper.selectList(null);
    }

    @Override
    public List<BaseCategory3> getCategory3(Long category2Id) {
        QueryWrapper<BaseCategory3> wrapper = new QueryWrapper<>();
        wrapper.eq("category2_id",category2Id);
        return category3Mapper.selectList(wrapper);    }

    @Override
    public BaseCategoryView getCategoryView(Long category3Id) {
        QueryWrapper<BaseCategoryView> wrapper = new QueryWrapper<>();
        wrapper.eq("category3_id",category3Id);
        return baseCategoryViewMapper.selectOne(wrapper);
    }

    @Override
    public List<BaseCategory2> getCategory2(Long category1Id) {
        QueryWrapper<BaseCategory2> wrapper = new QueryWrapper<>();
        wrapper.eq("category1_id", category1Id);
        return category2Mapper.selectList(wrapper);    }
}
