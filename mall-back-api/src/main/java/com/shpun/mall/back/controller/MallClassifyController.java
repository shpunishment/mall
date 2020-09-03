package com.shpun.mall.back.controller;

import com.shpun.mall.common.model.MallClassify;
import com.shpun.mall.common.model.vo.MallClassifyVo;
import com.shpun.mall.common.service.MallClassifyService;
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
@Api(tags = "商品分类控制器")
@RequestMapping("/api/classify")
@RestController
public class MallClassifyController {

    @Autowired
    private MallClassifyService classifyService;

    @ApiOperation("获取商品分类")
    @GetMapping("/list")
    public List<MallClassifyVo> list() {
        return classifyService.getVoList();
    }

    @ApiOperation("新增商品分类")
    @PostMapping("/add")
    public void add(@RequestBody MallClassify classify) {
        classifyService.insertSelective(classify);
    }

    @ApiOperation("删除商品分类")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "classifyId", value = "分类id", dataType = "Integer")
    })
    @GetMapping("/delete/{classifyId}")
    public void delete(@PathVariable("classifyId") Integer classifyId){
        classifyService.deleteByPrimaryKey(classifyId);
    }

    @ApiOperation("更新商品分类")
    @PostMapping("/update")
    public void update(@RequestBody MallClassify classify) {
        classifyService.updateByPrimaryKeySelective(classify);
    }

    @ApiOperation("上移商品分类")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "classifyId", value = "分类id", dataType = "Integer")
    })
    @GetMapping("/up/{classifyId}")
    public void up(@PathVariable("classifyId") Integer classifyId) {
        classifyService.up(classifyId);
    }

    @ApiOperation("下移商品分类")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "classifyId", value = "分类id", dataType = "Integer")
    })
    @GetMapping("/down/{classifyId}")
    public void down(@PathVariable("classifyId") Integer classifyId) {
        classifyService.down(classifyId);
    }

    @ApiOperation("置顶商品分类")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "classifyId", value = "分类id", dataType = "Integer")
    })
    @GetMapping("/top/{classifyId}")
    public void top(@PathVariable("classifyId") Integer classifyId) {
        classifyService.top(classifyId);
    }

}
