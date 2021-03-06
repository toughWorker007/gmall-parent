package com.atguigu.gmall.product.mapper;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @desc
 */
@Mapper
public interface BaseAttrMapper extends BaseMapper<BaseAttrInfo> {
    List<BaseAttrInfo> selectAttrInfoList(@Param("category_level") int categoryLevel
                , @Param("category_id") Long categoryId);
}
