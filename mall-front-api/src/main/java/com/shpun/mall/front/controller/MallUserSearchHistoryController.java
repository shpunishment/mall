package com.shpun.mall.front.controller;

import com.shpun.mall.common.enums.MallUserSearchHistoryTypeEnums;
import com.shpun.mall.common.service.MallUserSearchHistoryService;
import com.shpun.mall.front.security.SecurityUserUtils;
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
 * @Date: 2020/8/23 16:03
 */
@Api(tags = "用户查询历史控制器")
@RequestMapping("/api/user/history")
@RestController
public class MallUserSearchHistoryController {

    @Autowired
    private MallUserSearchHistoryService userSearchHistoryService;

    @ApiOperation("获取用户前十商品查询历史")
    @GetMapping("/product")
    public List<String> product() {
        return userSearchHistoryService.getTop10ByUserId(SecurityUserUtils.getUserId(), MallUserSearchHistoryTypeEnums.PRODUCT.getValue());
    }

    @ApiOperation("获取用户前十订单查询历史")
    @GetMapping("/order")
    public List<String> order() {
        return userSearchHistoryService.getTop10ByUserId(SecurityUserUtils.getUserId(), MallUserSearchHistoryTypeEnums.ORDER.getValue());
    }

}
