package com.shpun.mall.back.controller;

import com.shpun.mall.common.service.MallFileServerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/29 13:43
 */
@Api(tags = "文件服务器控制器")
@RequestMapping("/api/file")
@RestController
public class MallFileServerController {

    @Autowired
    private MallFileServerService fileServerService;

    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public Integer upload(@RequestParam(value = "file") MultipartFile file) {
        return fileServerService.saveUploadFile(file);
    }

}
