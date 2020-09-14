package com.shpun.mall.front.controller;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.model.MallCart;
import com.shpun.mall.common.model.vo.MallProductVo;
import com.shpun.mall.common.service.MallCartService;
import com.shpun.mall.common.service.MallProductService;
import com.shpun.mall.front.security.SecurityUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/23 16:02
 */
@Api(tags = "购物车控制器")
@RequestMapping("/api/cart")
@RestController
@Validated
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
    public PageInfo<MallProductVo> page(@RequestParam(value = "offset",defaultValue = "0") @Min(0) @Max(2147483647) Integer offset,
                                        @RequestParam(value = "limit",defaultValue = "10") @Min(1) @Max(2147483647) Integer limit) {
        PageInfo<MallProductVo> productVoPageInfo = cartService.getVoPageByUserId(SecurityUserUtils.getUserId(), offset, limit);

        productService.additionalVoList(productVoPageInfo.getList(), null, true);
        return productVoPageInfo;
    }

    @ApiOperation("获取用户购物车")
    @GetMapping("/list")
    public List<MallProductVo> list() {
        List<MallProductVo> productVoList = cartService.getStockAndNoStockVoList(SecurityUserUtils.getUserId());

        productService.additionalVoList(productVoList, null, true);
        return productVoList;
    }

    @ApiOperation("添加/修改购物车中商品")
    @PostMapping("/add")
    public void add(@RequestBody @Validated(MallCart.Add.class) MallCart cart){
        cart.setUserId(SecurityUserUtils.getUserId());
        cartService.addOrUpdate(cart);
    }

    @ApiOperation("清空购物车")
    @GetMapping("/clear")
    public void clear() {
        cartService.deleteAllByUserId(SecurityUserUtils.getUserId());

        // 删除购物车缓存
        cartService.deleteCache(SecurityUserUtils.getUserId());
    }

}
