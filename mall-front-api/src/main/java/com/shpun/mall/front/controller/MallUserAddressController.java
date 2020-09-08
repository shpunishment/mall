package com.shpun.mall.front.controller;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.exception.MallError;
import com.shpun.mall.common.exception.MallException;
import com.shpun.mall.common.model.MallUserAddress;
import com.shpun.mall.common.model.vo.MallUserAddressVo;
import com.shpun.mall.common.service.MallUserAddressService;
import com.shpun.mall.front.security.SecurityUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/23 15:54
 */
@Api(tags = "用户地址控制器")
@RequestMapping("/api/user/address")
@RestController
@Validated
public class MallUserAddressController {

    @Autowired
    private MallUserAddressService userAddressService;

    @ApiOperation("分页查看用户地址")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "offset", value = "偏移量", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer")
    })
    @GetMapping("/page")
    public PageInfo<MallUserAddressVo> page(@RequestParam(value = "offset",defaultValue = "0") @Min(0) @Max(2147483647) Integer offset,
                                            @RequestParam(value = "limit",defaultValue = "10") @Min(1) @Max(2147483647) Integer limit) {

        PageInfo<MallUserAddressVo> userAddressVoPageInfo = userAddressService.getVoPageByUserId(SecurityUserUtils.getUserId(), offset, limit);
        return userAddressVoPageInfo;
    }

    @ApiOperation("新增用户地址")
    @PostMapping("/add")
    public void add(@RequestBody @Validated(MallUserAddress.Add.class) MallUserAddress userAddress) {
        userAddress.setUserId(SecurityUserUtils.getUserId());
        userAddressService.insertSelective(userAddress);

        // 删除用户地址缓存
        userAddressService.deleteCache(SecurityUserUtils.getUserId());
    }

    @ApiOperation("删除用户地址")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "addressId", value = "收货地址id", dataType = "Integer")
    })
    @GetMapping("/delete/{addressId}")
    public void delete(@PathVariable("addressId") @Min(1) @Max(2147483647) Integer addressId) {
        userAddressService.deleteByPrimaryKey(addressId);

        // 删除用户地址缓存
        userAddressService.deleteCache(SecurityUserUtils.getUserId());
    }

    @ApiOperation("更新用户地址")
    @PostMapping("/update")
    public void update(@RequestBody @Validated(MallUserAddress.Update.class) MallUserAddress userAddress) {
        userAddress.setUserId(SecurityUserUtils.getUserId());
        userAddressService.updateByPrimaryKeySelective(userAddress);

        // 删除用户地址缓存
        userAddressService.deleteCache(SecurityUserUtils.getUserId());
    }

    @ApiOperation("根据地址id获取用户地址")
    @GetMapping("/{addressId}")
    public MallUserAddressVo get(@PathVariable("addressId") @Min(1) @Max(2147483647) Integer addressId) {
        MallUserAddress userAddress = userAddressService.selectByPrimaryKey(addressId);
        if (!userAddress.getUserId().equals(SecurityUserUtils.getUserId())) {
            throw new MallException(MallError.MallErrorEnum.INTERNAL_SYSTEM_ERROR);
        }

        return userAddressService.getVoByAddressId(addressId);
    }

}
