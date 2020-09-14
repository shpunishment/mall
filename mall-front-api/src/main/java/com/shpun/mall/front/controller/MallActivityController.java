package com.shpun.mall.front.controller;

import com.shpun.mall.common.exception.MallError;
import com.shpun.mall.common.exception.MallException;
import com.shpun.mall.common.model.vo.MallActivityClassifyVo;
import com.shpun.mall.common.model.vo.MallProductVo;
import com.shpun.mall.common.service.MallActivityClassifyProductService;
import com.shpun.mall.common.service.MallActivityClassifyService;
import com.shpun.mall.common.service.MallActivityService;
import com.shpun.mall.common.service.MallProductService;
import com.shpun.mall.front.security.SecurityUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Iterator;
import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/7 13:39
 */
@Api(tags = "活动控制器")
@RequestMapping("/api/activity")
@RestController
@Validated
public class MallActivityController {

    @Autowired
    private MallActivityService activityService;

    @Autowired
    private MallActivityClassifyService activityClassifyService;

    @Autowired
    private MallActivityClassifyProductService activityClassifyProductService;

    @Autowired
    private MallProductService productService;

    @ApiOperation("根据活动id获取活动分类和商品")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "activityId", value = "活动id", dataType = "Integer")
    })
    @GetMapping("/{activityId}")
    public List<MallActivityClassifyVo> getByActivityId(@PathVariable("activityId") @Min(1) @Max(2147483647) Integer activityId) {
        Boolean isActive = activityService.isActive(activityId);
        if (isActive) {
            List<MallActivityClassifyVo> activityClassifyVoList = activityClassifyService.getVoByActivityId(activityId);
            if (CollectionUtils.isNotEmpty(activityClassifyVoList)) {
                for (Iterator<MallActivityClassifyVo> iterator = activityClassifyVoList.iterator(); iterator.hasNext();) {
                    MallActivityClassifyVo activityClassifyVo = iterator.next();
                    List<MallProductVo> productVoList = activityClassifyProductService.getVoListByClassifyId(activityClassifyVo.getClassifyId());
                    if (CollectionUtils.isNotEmpty(productVoList)) {
                        // 检查商品是否在限时抢购，在用户购物车中
                        productService.additionalVoList(productVoList, SecurityUserUtils.getUserId(), true);
                        activityClassifyVo.setProductVoList(productVoList);
                    } else {
                        iterator.remove();
                    }
                }
            }
            return activityClassifyVoList;
        } else {
            throw new MallException(MallError.MallErrorEnum.ACTIVITY_NOT_FOUND);
        }
    }

}
