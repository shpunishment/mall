package com.shpun.mall.common.mapper;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.model.MallProduct;
import com.shpun.mall.common.model.vo.MallProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallProductMapper {

    int deleteByPrimaryKey(Integer productId);

    int insertSelective(MallProduct record);

    MallProduct selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(MallProduct record);

    MallProduct lockStock(Integer productId);

    MallProductVo getVoByProductId(Integer productId);

    List<MallProductVo> getVoListFilterByClassifyId(@Param("classifyId") Integer classifyId,
                                                    @Param("inStock") Integer inStock,
                                                    @Param("priceSort") Integer priceSort);

    List<MallProductVo> getVoListFilterByName(@Param("productName") String productName,
                                              @Param("inStock") Integer inStock,
                                              @Param("priceSort") Integer priceSort);

    List<MallProductVo> getHotVoList();

    MallProductVo getDetailVo(Integer productId);

    List<MallProduct> getListByClassifyId(Integer classifyId);

    List<MallProductVo> getVoListFilterByClassifyIdList(@Param("classifyIdList") List<Integer> classifyIdList,
                                                        @Param("inStock") Integer inStock,
                                                        @Param("priceSort") Integer priceSort);

    List<MallProductVo> getVoListByProductIdList(@Param("productIdList") List<Integer> productIdList,
                                                 @Param("inStock") Integer inStock,
                                                 @Param("priceSort") Integer priceSort);

}