package com.shpun.mall.front.controller;

import com.shpun.mall.common.model.vo.MallClassifyVo;
import com.shpun.mall.common.service.MallClassifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/23 15:47
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

}
