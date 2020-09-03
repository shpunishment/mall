package com.shpun.mall.back.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.model.MallFlash;
import com.shpun.mall.common.service.MallFlashService;
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
@Api(tags = "限时抢购控制器")
@RequestMapping("/api/flash")
@RestController
public class MallFlashController {

    @Autowired
    private MallFlashService flashService;

    @ApiOperation("分页获取限时抢购")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "offset", value = "偏移量", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer")
    })
    @GetMapping("/page")
    public PageInfo<MallFlash> page(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                    @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        PageHelper.offsetPage(offset, limit);
        PageInfo<MallFlash> flashPageInfo = new PageInfo<>(flashService.getList());
        return flashPageInfo;
    }

    @ApiOperation("添加限时抢购")
    @PostMapping("/add")
    public void add(@RequestBody MallFlash flash) {
        flashService.insertSelective(flash);
    }

    @ApiOperation("删除限时抢购")
    @PostMapping("/delete/{flashId}")
    public void delete(@PathVariable("flashId") Integer flashId) {
        flashService.deleteByPrimaryKey(flashId);
    }

    @ApiOperation("更新限时抢购")
    @PostMapping("/update")
    public void update(@RequestBody MallFlash flash) {
        flashService.updateByPrimaryKeySelective(flash);
    }

}
