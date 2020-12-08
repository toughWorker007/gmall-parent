package com.atguigu.gmall.product.mapper;

import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @desc
 */
@Mapper
public interface SpuSaleAttrMapper extends BaseMapper<SpuSaleAttr> {
    List<SpuSaleAttr> selectSpuSaleAttrListCheckBySku(Long spuId, Long skuId);

    List<Map> selectSaleAttrValuesBySpu(Long spuId);

}
