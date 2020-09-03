package com.shpun.mall.back.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.model.MallFlashItem;
import com.shpun.mall.common.service.MallFlashItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/29 13:45
 */
@Api(tags = "限时抢购商品控制器")
@RequestMapping("/api/flashItem")
@RestController
public class MallFlashItemController {

    @Autowired
    private MallFlashItemService flashItemService;

    @ApiOperation("分页获取限时抢购商品")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "flashId", value = "限时抢购id", dataType = "Integer"),
            @ApiImplicitParam(name = "offset", value = "偏移量", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer")
    })
    @GetMapping("/page")
    public PageInfo<MallFlashItem> page(@RequestParam("flashId") Integer flashId,
                                        @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                        @RequestParam(value = "limit", defaultValue = "10") Integer limit) {

        PageHelper.offsetPage(offset, limit);
        PageInfo<MallFlashItem> flashItemPageInfo = new PageInfo<>(flashItemService.getByFlashId(flashId));
        return flashItemPageInfo;
    }

    @ApiOperation("添加限时抢购商品")
    @PostMapping("/add")
    public void add(@RequestBody MallFlashItem flashItem) {
        flashItemService.insertSelective(flashItem);
    }

    @ApiOperation("删除限时抢购商品")
    @GetMapping("/delete/{flashItemId}")
    public void delete(@PathVariable("flashItemId") Integer flashItemId) {
        flashItemService.deleteByPrimaryKey(flashItemId);
    }

    @ApiOperation("更新限时抢购商品")
    @PostMapping("/update")
    public void update(@RequestBody MallFlashItem flashItem) {
        flashItemService.updateByPrimaryKeySelective(flashItem);
    }

}
