package com.shpun.mall.front.controller;

import com.shpun.mall.common.service.MallFileServerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/23 16:57
 */
@Api(tags = "文件服务器控制器")
@RequestMapping("/api/file")
@RestController
@Validated
public class MallFileServerController {

    @Autowired
    private MallFileServerService fileServerService;

    @ApiOperation("获取图片")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "fileId", value = "文件id", dataType = "Integer")
    })
    @GetMapping("/img/{fileId}")
    public void getImgFile(@PathVariable("fileId") @Min(1) @Max(2147483647) Integer fileId, HttpServletResponse httpServletResponse) {
        fileServerService.getImgFile(fileId, httpServletResponse);
    }

}
