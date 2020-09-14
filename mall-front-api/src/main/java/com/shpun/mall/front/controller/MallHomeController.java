package com.shpun.mall.front.controller;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.enums.MallUserSearchHistoryTypeEnums;
import com.shpun.mall.common.model.vo.*;
import com.shpun.mall.common.service.*;
import com.shpun.mall.front.security.SecurityUserUtils;
import io.swagger.annotations.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/23 16:03
 */
@Api(tags = "首页控制器")
@RequestMapping("/api/home")
@RestController
@Validated
public class MallHomeController {

    @Autowired
    private MallProductService productService;

    @Autowired
    private MallClassifyService classifyService;

    @Autowired
    private MallFlashService flashService;

    @Autowired
    private MallUserSearchHistoryService userSearchHistoryService;

    @Autowired
    private MallActivityService activityService;

    @Autowired
    private MallFlashItemService flashItemService;

    @Autowired
    private MallCartService cartService;

    @ApiOperation("搜索商品")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "productName", value = "商品名称", dataType = "String"),
            @ApiImplicitParam(name = "inStock", value = "有货过滤，0不过滤，1过滤", dataType = "Integer"),
            @ApiImplicitParam(name = "priceSort", value = "价格排序，1顺序，2倒序", dataType = "Integer"),
            @ApiImplicitParam(name = "offset", value = "偏移量", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer")
    })
    @GetMapping("/search")
    public PageInfo<MallProductVo> search(@RequestParam(value = "productName", defaultValue = "") @Length(max = 20) String productName,
                                          @RequestParam(value = "inStock", defaultValue = "0") @Min(0) @Max(1) Integer inStock,
                                          @RequestParam(value = "priceSort", defaultValue = "1") @Min(1) @Max(2) Integer priceSort,
                                          @RequestParam(value = "offset", defaultValue = "0") @Min(0) @Max(2147483647) Integer offset,
                                          @RequestParam(value = "limit", defaultValue = "10") @Min(1) @Max(2147483647) Integer limit) {

        if (SecurityUserUtils.getCurrentUser() != null) {
            // 添加用户搜索历史
            userSearchHistoryService.insertOrUpdate(SecurityUserUtils.getUserId(), productName, MallUserSearchHistoryTypeEnums.PRODUCT.getValue());
        }

        PageInfo<MallProductVo> productVoPageInfo = productService.getVoPageByFilter(productName, inStock, priceSort, offset, limit);

        // 检查商品是否在限时抢购，在用户购物车中
        productService.additionalVoList(productVoPageInfo.getList(), SecurityUserUtils.getUserId(), true);
        return productVoPageInfo;
    }

    @ApiOperation("热门搜索词")
    @GetMapping("/getHotKeyword")
    public List<String> getHotKeyword() {
        return userSearchHistoryService.getHotByType(Const.DEFAULT_LIMIT_HOT_KEYWORD, MallUserSearchHistoryTypeEnums.PRODUCT.getValue());
    }

    @ApiOperation("获取今日活动")
    @GetMapping("/getTodayActivity")
    public List<MallActivityVo> getTodayActivity() {
        return activityService.getTodayVoList();
    }

    @ApiOperation("获取首页分类")
    @GetMapping("/getHomeClassify")
    public List<MallClassifyVo> getHomeClassify() {
        return classifyService.getHomeClassify(Const.PRODUCT_CLASSIFY_ROOT_ID);
    }

    @ApiOperation("分页获取热门商品")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "offset", value = "偏移量", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer")
    })
    @GetMapping("/getHotProduct")
    public PageInfo<MallProductVo> getHotProductVo(@RequestParam(value = "offset", defaultValue = "0") @Min(0) @Max(2147483647) Integer offset,
                                                   @RequestParam(value = "limit", defaultValue = "10") @Min(1) @Max(2147483647) Integer limit) {

        PageInfo<MallProductVo> productVoPageInfo = productService.getHotVoPage(offset, limit);

        // 检查商品是否在限时抢购，在用户购物车中
        productService.additionalVoList(productVoPageInfo.getList(), SecurityUserUtils.getUserId(), true);
        return productVoPageInfo;
    }

    @ApiOperation("获取今日限时抢购时间")
    @GetMapping("/getTodayFlash")
    public List<MallFlashVo> getTodayFlash() {
        return flashService.getTodayVoList();
    }

    @ApiOperation("根据限时抢购id获取限时抢购商品")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "flashId", value = "限时抢购id", dataType = "Integer"),
            @ApiImplicitParam(name = "offset", value = "偏移量", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer")
    })
    @GetMapping("/getProductByFlashId/{flashId}")
    public PageInfo<MallProductVo> getProductByFlashId(@PathVariable("flashId") @Min(1) @Max(2147483647) Integer flashId,
                                                       @RequestParam(value = "offset", defaultValue = "0") @Min(0) @Max(2147483647) Integer offset,
                                                       @RequestParam(value = "limit", defaultValue = "10") @Min(1) @Max(2147483647) Integer limit) {

        PageInfo<MallProductVo> productVoPageInfo = flashItemService.getVoPageByFlashId(flashId, offset, limit);

        // 赋值库存比例，检查商品是否在用户购物车中
        flashItemService.additionalVoList(productVoPageInfo.getList(), SecurityUserUtils.getUserId(), flashId);
        return productVoPageInfo;
    }

    @ApiOperation("获取当前购物车商品数量")
    @GetMapping("/getCartAmount")
    public Integer getCartAmount() {
        Integer amount = 0;
        if (SecurityUserUtils.getUserId() != null) {
            amount = cartService.getAvailableCartSum(SecurityUserUtils.getUserId());
            amount = amount == null ? 0 : amount;
        }
        return amount;
    }

}
