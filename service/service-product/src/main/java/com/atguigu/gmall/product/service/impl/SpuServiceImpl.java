package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.SpuImage;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.product.SpuSaleAttrValue;
import com.atguigu.gmall.product.mapper.SpuImageMapper;
import com.atguigu.gmall.product.mapper.SpuInfoMapper;
import com.atguigu.gmall.product.mapper.SpuSaleAttrMapper;
import com.atguigu.gmall.product.mapper.SpuSaleAttrValueMapper;
import com.atguigu.gmall.product.service.SpuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @desc
 */
@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    SpuInfoMapper spuInfoMapper;
    @Autowired
    SpuImageMapper spuImageMapper;
    @Autowired
    SpuSaleAttrMapper spuSaleAttrMapper;
    @Autowired
    SpuSaleAttrValueMapper spuSaleAttrValueMapper;

    @Override
    public IPage<SpuInfo> spuInfoIPage(IPage<SpuInfo> page, Long category3Id) {
        QueryWrapper<SpuInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("category3_id", category3Id);
        spuInfoMapper.selectPage(page, wrapper);
        return page;
    }

    @Override
    public void saveSpuInfo(SpuInfo spuInfo) {
        spuInfoMapper.insert(spuInfo);
        Long id = spuInfo.getId();

        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        if (spuImageList != null) {
            for (SpuImage spuImage : spuImageList) {
                spuImage.setSpuId(id);
                spuImageMapper.insert(spuImage);
            }
        }
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        if (spuSaleAttrList != null) {
            for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
                spuSaleAttr.setSpuId(id);
                spuSaleAttrMapper.insert(spuSaleAttr);

                List<SpuSaleAttrValue> saleAttrSpuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
                if (saleAttrSpuSaleAttrValueList != null) {
                    for (SpuSaleAttrValue attrValue : saleAttrSpuSaleAttrValueList) {
                        Long baseSaleAttrId = spuSaleAttr.getBaseSaleAttrId();
                        attrValue.setSpuId(id);
                        attrValue.setSaleAttrName(spuSaleAttr.getSaleAttrName());
                        attrValue.setBaseSaleAttrId(baseSaleAttrId);
                        spuSaleAttrValueMapper.insert(attrValue);
                    }
                }

            }

        }
    }

    @Override
    public List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(Long spuId, Long skuId) {
        List<SpuSaleAttr> spuSaleAttrs = spuSaleAttrMapper.selectSpuSaleAttrListCheckBySku(spuId,skuId);
        return spuSaleAttrs;
    }

    @Override
    public Map<String, Long> getSaleAttrValuesBySpu(Long spuId) {
        List<Map> saleMaps = spuSaleAttrMapper.selectSaleAttrValuesBySpu(spuId);
        Map<String,Long> jsonMap = new HashMap<>();
        for (Map saleMap : saleMaps) {
            String k  = (String)saleMap.get("value_Ids");
            Long v = (Long)saleMap.get("sku_id");
            jsonMap.put(k,v);
        }
        return jsonMap;
    }


}
