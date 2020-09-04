package com.shpun.mall.front.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.model.MallUserFootprint;
import com.shpun.mall.common.model.vo.MallProductVo;
import com.shpun.mall.common.service.MallProductService;
import com.shpun.mall.common.service.MallUserFootprintService;
import com.shpun.mall.front.security.SecurityUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/23 16:03
 */
@Api(tags = "用户足迹控制器")
@RequestMapping("/api/user/footprint")
@RestController
public class MallUserFootprintController {

    @Autowired
    private MallUserFootprintService userFootprintService;

    @Autowired
    private MallProductService productService;

    @ApiOperation("分页获取用户足迹")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "offset", value = "偏移量", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer")
    })
    @GetMapping("/page")
    public PageInfo<MallProductVo> list(@RequestParam(value = "offset",defaultValue = "0") Integer offset,
                                              @RequestParam(value = "limit",defaultValue = "10") Integer limit) {

        PageInfo<MallProductVo> productVoPageInfo = userFootprintService.getVoPageByFootprint(SecurityUserUtils.getUserId(), offset, limit);

        productService.additionalVoList(productVoPageInfo.getList(), SecurityUserUtils.getUserId(), true);
        return productVoPageInfo;
    }

    @ApiOperation("添加用户足迹")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "productId", value = "商品id", dataType = "Integer")
    })
    @GetMapping("/add/{productId}")
    public void add(@PathVariable("productId") Integer productId) {
        MallUserFootprint userFootprint = new MallUserFootprint();
        userFootprint.setUserId(SecurityUserUtils.getUserId());
        userFootprint.setProductId(productId);
        userFootprintService.insertSelective(userFootprint);

        // 删除用户足迹缓存
        userFootprintService.deleteCache(SecurityUserUtils.getUserId());
    }

    @ApiOperation("删除足迹")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "footprintId", value = "足迹id", dataType = "Integer")
    })
    @GetMapping("/delete/{footprintId}")
    public void delete(@PathVariable("footprintId") Integer footprintId) {
        userFootprintService.deleteByPrimaryKey(footprintId);

        // 删除用户足迹缓存
        userFootprintService.deleteCache(SecurityUserUtils.getUserId());
    }

}
