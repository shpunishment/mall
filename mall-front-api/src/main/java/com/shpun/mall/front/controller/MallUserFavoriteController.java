package com.shpun.mall.front.controller;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.model.MallUserFavorite;
import com.shpun.mall.common.model.vo.MallProductVo;
import com.shpun.mall.common.service.MallProductService;
import com.shpun.mall.common.service.MallUserFavoriteService;
import com.shpun.mall.front.security.SecurityUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/23 16:03
 */
@Api(tags = "用户收藏控制器")
@RequestMapping("/api/user/favorite")
@RestController
public class MallUserFavoriteController {

    @Autowired
    private MallUserFavoriteService userFavoriteService;

    @Autowired
    private MallProductService productService;

    @ApiOperation("分页获取收藏商品")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "offset", value = "偏移量", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer")
    })
    @GetMapping("/page")
    public PageInfo<MallProductVo> list(@RequestParam(value = "offset",defaultValue = "0") Integer offset,
                                        @RequestParam(value = "limit",defaultValue = "10") Integer limit) {

        PageInfo<MallProductVo> productVoPageInfo = userFavoriteService.getVoPageByFavorite(SecurityUserUtils.getUserId(), offset, limit);

        // 检查商品是否在限时抢购，在用户购物车中
        productService.additionalVoList(productVoPageInfo.getList(), SecurityUserUtils.getUserId(), true);
        return productVoPageInfo;
    }

    @ApiOperation("收藏商品")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "productId", value = "商品id", dataType = "Integer")
    })
    @GetMapping("/favorite/{productId}")
    public void favorite(@PathVariable("productId") @Min(1) @Max(2147483647) Integer productId) {
        MallUserFavorite userFavorite = new MallUserFavorite();
        userFavorite.setProductId(productId);
        userFavorite.setUserId(SecurityUserUtils.getUserId());
        userFavoriteService.insertSelective(userFavorite);

        // 删除用户收藏缓存
        userFavoriteService.deleteCache(SecurityUserUtils.getUserId());
    }

    @ApiOperation("取消收藏商品")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "favoriteId", value = "收藏id", dataType = "Integer")
    })
    @GetMapping("/unfavorite/{favoriteId}")
    public void unfavorite(@PathVariable("favoriteId") @Min(1) @Max(2147483647) Integer favoriteId) {
        userFavoriteService.deleteByPrimaryKey(favoriteId);

        // 删除用户收藏缓存
        userFavoriteService.deleteCache(SecurityUserUtils.getUserId());
    }

}
