package com.shpun.mall.front.controller;

import com.shpun.mall.common.enums.MallUserCouponGetTypeEnums;
import com.shpun.mall.common.exception.MallException;
import com.shpun.mall.common.model.MallUser;
import com.shpun.mall.common.model.MallUserCoupon;
import com.shpun.mall.common.model.vo.MallUserVo;
import com.shpun.mall.common.service.MallCouponService;
import com.shpun.mall.common.service.MallUserCouponService;
import com.shpun.mall.common.service.MallUserService;
import com.shpun.mall.front.security.SecurityUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/23 15:52
 */
@Api(tags = "用户控制器")
@RequestMapping("/api/user")
@RestController
public class MallUserController {

    @Autowired
    private MallUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MallCouponService couponService;

    @Autowired
    private MallUserCouponService userCouponService;

    @ApiOperation("注册")
    @PostMapping("/register")
    public void register(@RequestBody MallUser user) {
        if (userService.isExist(user.getUsername())) {
            throw new MallException("用户名已存在");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.insertSelective(user);

        // 注册成功，赠予优惠券
        List<Integer> newUserCouponIdList = couponService.getNewUserCouponId();
        if (CollectionUtils.isNotEmpty(newUserCouponIdList)) {
            newUserCouponIdList.forEach(couponId -> {
                MallUserCoupon userCoupon = new MallUserCoupon();
                userCoupon.setUserId(user.getUserId());
                userCoupon.setCouponId(couponId);
                userCoupon.setGetType(MallUserCouponGetTypeEnums.BACKSTAGE.getValue());
                userCouponService.insertSelective(userCoupon);
            });
        }
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/info")
    public MallUserVo info() {
        MallUserVo userVo = userService.getVoByUserId(SecurityUserUtils.getUserId());
        return userVo;
    }

    @ApiOperation("更新用户信息")
    @GetMapping("/update")
    public void update(@RequestBody MallUser user) {
        user.setUserId(SecurityUserUtils.getUserId());
        userService.updateByPrimaryKeySelective(user);

        // 删除用户信息缓存
        userService.deleteCache(user.getUserId());
    }

    @ApiOperation("修改密码")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "oldPassword", value = "原密码", dataType = "String"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", dataType = "String")
    })
    @PostMapping("/changePassword")
    public void changePassword(@RequestParam("oldPassword") String oldPassword,
                               @RequestParam("newPassword") String newPassword) {
        MallUser user = userService.selectByPrimaryKey(SecurityUserUtils.getUserId());
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userService.updateByPrimaryKeySelective(user);
        } else {
            throw new MallException("原密码错误");
        }
    }

}
