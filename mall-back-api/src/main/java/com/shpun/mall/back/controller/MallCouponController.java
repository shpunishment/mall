package com.shpun.mall.back.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.model.MallCoupon;
import com.shpun.mall.common.service.MallCouponService;
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
 * @Date: 2020/9/2 17:16
 */
@Api(tags = "优惠券控制器")
@RequestMapping("/api/coupon")
@RestController
public class MallCouponController {

    @Autowired
    private MallCouponService couponService;

    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "offset", value = "偏移量", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer")
    })
    @GetMapping("/page")
    public PageInfo<MallCoupon> page(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                     @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        PageHelper.offsetPage(offset, limit);
        PageInfo<MallCoupon> couponPageInfo = new PageInfo<>(couponService.getList());
        return couponPageInfo;
    }

    @ApiOperation("添加优惠券")
    @PostMapping("/add")
    public void add(@RequestBody MallCoupon coupon) {
        couponService.insertSelective(coupon);
    }

    @ApiOperation("添加优惠券，关联二级分类")
    @PostMapping("/addWithClassify")
    public void addWithClassify(@RequestParam("coupon") MallCoupon coupon,
                                @RequestParam("classifyIdList") List<Integer> classifyIdList) {
        couponService.insertWithClassifyIdList(coupon, classifyIdList);
    }

    @ApiOperation("添加优惠券，关联商品")
    @PostMapping("/addWithProduct")
    public void addWithProduct(@RequestParam("coupon") MallCoupon coupon,
                                @RequestParam("productIdList") List<Integer> productIdList) {
        couponService.insertWithProductIdList(coupon, productIdList);
    }

    @ApiOperation("更新优惠券")
    @PostMapping("/update")
    public void update(@RequestBody MallCoupon coupon) {
        couponService.updateByPrimaryKeySelective(coupon);
    }

    @ApiOperation("更新优惠券，关联二级分类")
    @PostMapping("/updateWithClassify")
    public void updateWithClassify(@RequestParam("coupon") MallCoupon coupon,
                                @RequestParam("classifyIdList") List<Integer> classifyIdList) {
        couponService.updateWithClassifyIdList(coupon, classifyIdList);
    }

    @ApiOperation("更新优惠券，关联商品")
    @PostMapping("/updateWithProduct")
    public void updateWithProduct(@RequestParam("coupon") MallCoupon coupon,
                               @RequestParam("productIdList") List<Integer> productIdList) {
        couponService.updateWithProductIdList(coupon, productIdList);
    }

    @GetMapping("/delete/{couponId}")
    public void delete(@PathVariable("couponId") Integer couponId) {
        couponService.deleteByPrimaryKey(couponId);
    }

}
