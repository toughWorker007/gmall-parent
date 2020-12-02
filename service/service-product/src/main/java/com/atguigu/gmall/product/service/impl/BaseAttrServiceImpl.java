package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.model.product.BaseSaleAttr;
import com.atguigu.gmall.product.mapper.BaseAttrMapper;
import com.atguigu.gmall.product.mapper.BaseAttrValueMapper;
import com.atguigu.gmall.product.mapper.BaseSaleAttrMapper;
import com.atguigu.gmall.product.service.BaseAttrService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @desc
 */
@Service
public class BaseAttrServiceImpl implements BaseAttrService {

    @Autowired
    BaseAttrMapper baseAttrMapper;
    @Autowired
    BaseAttrValueMapper baseAttrValueMapper;

    @Autowired
    BaseSaleAttrMapper baseSaleAttrMapper;

    @Override
    public List<BaseAttrInfo> attrInfoList(Long category3Id) {
//        QueryWrapper<BaseAttrInfo> wrapper = new QueryWrapper<>();
//        wrapper.eq("category_id", category3Id);
//        List<BaseAttrInfo> attrInfos = baseAttrMapper.selectList(wrapper);
//
//        for (BaseAttrInfo attrInfo : attrInfos) {
//            Long attrId = attrInfo.getId();
//            QueryWrapper<BaseAttrValue> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("attr_id", attrId);
//            List<BaseAttrValue> valueList = baseAttrValueMapper.selectList(queryWrapper);
//            attrInfo.setAttrValueList(valueList);
//        }
//        return attrInfos;

        //减少磁盘io次数
        List<BaseAttrInfo> attrInfos = baseAttrMapper.selectAttrInfoList(3, category3Id);
        return attrInfos;
    }

    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        Long id = baseAttrInfo.getId();
        if (null!=id && id>0){
            baseAttrMapper.updateById(baseAttrInfo);
            QueryWrapper<BaseAttrValue> wrapper = new QueryWrapper<>();
            wrapper.eq("attr_id",id);
            baseAttrValueMapper.delete(wrapper);

        }else {
            baseAttrMapper.insert(baseAttrInfo);
        }
        List<BaseAttrValue> valueList = baseAttrInfo.getAttrValueList();
        for (BaseAttrValue attrValue : valueList) {
            attrValue.setAttrId(id);
            baseAttrValueMapper.insert(attrValue);
        }
    }

    @Override
    public List<BaseAttrValue> getAttrValueList(Long attrId) {
        QueryWrapper<BaseAttrValue> wrapper = new QueryWrapper<>();
        wrapper.eq("attr_id", attrId);
        List<BaseAttrValue> attrValueList = baseAttrValueMapper.selectList(wrapper);
        return attrValueList;
    }

    @Override
    public List<BaseSaleAttr> baseSaleAttrList() {
        return baseSaleAttrMapper.selectList(null);
    }
}
