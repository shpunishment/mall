package com.shpun.mall.back.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.model.MallProduct;
import com.shpun.mall.common.service.MallProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/29 13:44
 */
@Api(tags = "商品控制器")
@RequestMapping("/api/product")
@RestController
public class MallProductController {

    @Autowired
    private MallProductService productService;

    @ApiOperation("根据商品二级分类id分页获取商品")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "classifyId", value = "商品二级分类id", dataType = "Integer"),
            @ApiImplicitParam(name = "offset", value = "偏移量", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer")
    })
    @GetMapping("/getByClassifyId")
    public PageInfo<MallProduct> getByClassifyId(@RequestParam("classifyId") Integer classifyId,
                                                 @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                 @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        PageHelper.offsetPage(offset, limit);
        PageInfo<MallProduct> productPageInfo = new PageInfo<>(productService.getListByClassifyId(classifyId));
        return productPageInfo;
    }

    @ApiOperation("添加商品")
    @PostMapping("/add")
    public void add(@RequestBody MallProduct product) {
        productService.insertSelective(product);
    }

    @ApiOperation("添加商品，关联二级分类")
    @PostMapping("/addWithClassify")
    public void addWithClassify(@RequestParam("product") MallProduct product,
                                @RequestParam("classifyIdList") List<Integer> classifyIdList) {
        productService.insertWithClassifyIdList(product, classifyIdList);
    }

    @ApiOperation("删除商品")
    @GetMapping("/delete/{productId}")
    public void delete(@PathVariable("productId") Integer productId) {
        productService.deleteByPrimaryKey(productId);
    }

    @ApiOperation("更新商品")
    @PostMapping("/update")
    public void update(@RequestBody MallProduct product) {
        productService.insertSelective(product);
    }

    @ApiOperation("更新商品，关联二级分类")
    @PostMapping("/updateWithClassify")
    public void updateWithClassify(@RequestParam("product") MallProduct product,
                                   @RequestParam("classifyIdList") List<Integer> classifyIdList) {
        productService.updateWithClassifyIdList(product, classifyIdList);
    }

}
