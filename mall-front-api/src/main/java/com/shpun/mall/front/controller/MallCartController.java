package com.shpun.mall.front.controller;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.model.MallCart;
import com.shpun.mall.common.model.vo.MallCartVo;
import com.shpun.mall.common.model.vo.MallProductVo;
import com.shpun.mall.common.service.MallCartService;
import com.shpun.mall.common.service.MallProductService;
import com.shpun.mall.front.security.SecurityUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/23 16:02
 */
@Api(tags = "购物车控制器")
@RequestMapping("/api/cart")
@RestController
public class MallCartController {

    @Autowired
    private MallCartService cartService;

    @Autowired
    private MallProductService productService;

    @ApiOperation("分页获取购物车")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "offset", value = "偏移量", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer")
    })
    @GetMapping("/page")
    public PageInfo<MallCartVo> page(@RequestParam(value = "offset",defaultValue = "0") Integer offset,
                                     @RequestParam(value = "limit",defaultValue = "10") Integer limit) {

        PageInfo<MallCartVo> cartVoPageInfo = cartService.getVoPageByUserId(SecurityUserUtils.getUserId(), offset, limit);

        if (CollectionUtils.isNotEmpty(cartVoPageInfo.getList())) {
            cartVoPageInfo.getList().forEach(cartVo -> {
                productService.additionalVo(cartVo.getProductVo(), null, true, false);
            });
        }
        return cartVoPageInfo;
    }

    @ApiOperation("添加商品到购物车")
    @PostMapping("/add")
    public void add(@RequestBody MallCart cart){
        cart.setUserId(SecurityUserUtils.getUserId());
        cartService.insertSelective(cart);
    }

    @ApiOperation("更新购物车商品")
    @PostMapping("/update")
    public void update(@RequestBody MallCart cart) {
        cartService.updateByPrimaryKeySelective(cart);
    }

}
