package com.shpun.mall.back.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.model.MallBrand;
import com.shpun.mall.common.service.MallBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/29 13:43
 */
@Api(tags = "品牌控制器")
@RequestMapping("/api/brand")
@RestController
public class MallBrandController {

    @Autowired
    private MallBrandService brandService;

    @ApiOperation("分页获取品牌")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "offset", value = "偏移量", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer")
    })
    @GetMapping("/page")
    public PageInfo<MallBrand> page(@RequestParam(value = "offset",defaultValue = "0") Integer offset,
                                     @RequestParam(value = "limit",defaultValue = "10") Integer limit) {

        PageHelper.offsetPage(offset, limit);
        PageInfo<MallBrand> brandPageInfo = new PageInfo<>(brandService.getList());
        return brandPageInfo;
    }

    @ApiOperation("新增品牌")
    @PostMapping("/add")
    public void add(@RequestBody MallBrand brand) {
        brandService.insertSelective(brand);
    }

    @ApiOperation("删除品牌")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "brandId", value = "品牌id", dataType = "Integer")
    })
    @GetMapping("/delete/{brandId}")
    public void delete(@PathVariable("brandId") Integer brandId) {
        brandService.deleteByPrimaryKey(brandId);
    }

    @ApiOperation("新增品牌")
    @PostMapping("/update")
    public void update(@RequestBody MallBrand brand) {
        brandService.updateByPrimaryKeySelective(brand);
    }

}
