package com.shpun.mall.common.service;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.model.MallProduct;
import com.shpun.mall.common.model.vo.MallProductVo;
import com.sun.java.swing.plaf.windows.WindowsTextAreaUI;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:37
 */
public interface MallProductService {

    void deleteByPrimaryKey(Integer productId);

    void insertSelective(MallProduct record);

    MallProduct selectByPrimaryKey(Integer productId);

    void updateByPrimaryKeySelective(MallProduct record);

    void insertWithClassifyIdList(MallProduct product, List<Integer> classifyIdList);

    void updateWithClassifyIdList(MallProduct product, List<Integer> classifyIdList);

    MallProduct lockStock(Integer productId);

    void updateBatch(List<MallProduct> productList);

    /**
     * 判断商品是否限时抢购，替换库存，价格和限购数
     * @param product
     * @return
     */
    void replaceWithFlash(MallProduct product);

    void replaceWithFlash(MallProductVo productVo);

    /**
     * 检查商品库存和限购
     * @param productId
     * @param quantity
     */
    MallProduct checkProduct(Integer productId, Integer quantity);

    MallProductVo getVoByProductId(Integer productId);

    /**
     * 过滤获取商品vo
     * @param classifyId
     * @param inStock 有货
     * @param priceSort 价格排序，1顺序，2倒序
     * @return
     */
    List<MallProductVo> getVoListByFilter(Integer classifyId, Integer inStock, Integer priceSort);

    PageInfo<MallProductVo> getVoPageByFilter(Integer classifyId, Integer inStock, Integer priceSort, Integer offset, Integer limit);

    /**
     * 过滤获取商品vo
     * @param productName
     * @param inStock 有货
     * @param priceSort 价格排序，1顺序，2倒序
     * @return
     */
    List<MallProductVo> getVoListByFilter(String productName, Integer inStock, Integer priceSort);

    PageInfo<MallProductVo> getVoPageByFilter(String productName, Integer inStock, Integer priceSort, Integer offset, Integer limit);

    List<MallProductVo> getHotVoList();

    PageInfo<MallProductVo> getHotVoPage(Integer offset, Integer limit);

    MallProductVo getDetailVo(Integer productId);

    /**
     * 限时抢购替换库存，价格和限购数；
     * 添加用户购物车信息；
     * 添加用户收藏状态
     * @param productVo
     * @param userId
     * @param replaceWithFlash 是否需要查询限时抢购，替换库存，价格和限购数
     * @param favorite 是否需要查询用户收藏
     */
    void additionalVo(MallProductVo productVo, Integer userId, boolean replaceWithFlash, boolean favorite);

    /**
     * 限时抢购替换库存，价格和限购数；
     * 添加用户购物车信息；
     * 添加用户收藏状态
     * @param productVoList
     * @param userId
     * @param replaceWithFlash 是否需要查询限时抢购，替换库存，价格和限购数
     */
    void additionalVoList(List<MallProductVo> productVoList, Integer userId, boolean replaceWithFlash);

    List<MallProduct> getListByClassifyId(Integer classifyId);

    /**
     * 获取除了 notProductIdList 外的商品
     * @param notProductIdList
     * @param inStock
     * @param priceSort
     * @return
     */
    List<MallProductVo> getVoListByFilterNotProductIdList(List<Integer> notProductIdList, Integer inStock, Integer priceSort);

    PageInfo<MallProductVo> getVoPageByFilterNotProductIdList(List<Integer> notProductIdList, Integer inStock, Integer priceSort, Integer offset, Integer limit);

    /**
     * 获取 classifyIdList 下的，除了 notProductIdList 外的商品
     * @param classifyIdList
     * @param notProductIdList
     * @param inStock
     * @param priceSort
     * @return
     */
    List<MallProductVo> getVoListFilterByClassifyIdListAndNotProductIdList(List<Integer> classifyIdList, List<Integer> notProductIdList, Integer inStock, Integer priceSort);

    PageInfo<MallProductVo> getVoPageFilterByClassifyIdListAndNotProductIdList(List<Integer> classifyIdList, List<Integer> notProductIdList, Integer inStock, Integer priceSort, Integer offset, Integer limit);

    /**
     * 获取 productIdList 下的，除了 notProductIdList 外的商品
     * @param productIdList
     * @param notProductIdList
     * @param inStock
     * @param priceSort
     * @return
     */
    List<MallProductVo> getVoListFilterByProductIdListAndNotProductIdList(List<Integer> productIdList, List<Integer> notProductIdList, Integer inStock, Integer priceSort);

    PageInfo<MallProductVo> getVoPageFilterByProductIdListAndNotProductIdList(List<Integer> productIdList, List<Integer> notProductIdList, Integer inStock, Integer priceSort, Integer offset, Integer limit);

    /**
     * 下单成功，删除缓存
     */
    void deleteCache();

}
