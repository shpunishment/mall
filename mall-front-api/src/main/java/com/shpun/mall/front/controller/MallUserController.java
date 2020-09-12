package com.shpun.mall.front.controller;

import com.shpun.mall.common.enums.MallUserCouponGetTypeEnums;
import com.shpun.mall.common.exception.MallError;
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
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
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
    public void register(@RequestBody @Validated(MallUser.Register.class) MallUser user) {
        if (userService.isExist(user.getUsername())) {
            throw new MallException(MallError.MallErrorEnum.USERNAME_EXIST.format(user.getUsername()));
        }

        user.setNickname(user.getUsername());
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

        // 用户可用优惠券数量
        Integer couponCount = userCouponService.getAvailableCount(SecurityUserUtils.getUserId());
        userVo.setCouponCount(couponCount);
        return userVo;
    }

    @ApiOperation("更新用户信息")
    @PostMapping("/update")
    public void update(@RequestBody @Validated(MallUser.Update.class) MallUser user) {
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
    public void changePassword(@RequestParam("oldPassword") @NotBlank @Length(min = 32, max = 32) String oldPassword,
                               @RequestParam("newPassword") @NotBlank @Length(min = 32, max = 32) String newPassword) {
        MallUser user = userService.selectByPrimaryKey(SecurityUserUtils.getUserId());
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userService.updateByPrimaryKeySelective(user);
        } else {
            throw new MallException(MallError.MallErrorEnum.OLD_PASSWORD_ERROR);
        }
    }

}
