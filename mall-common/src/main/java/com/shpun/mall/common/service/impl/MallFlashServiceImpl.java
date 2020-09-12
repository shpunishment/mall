package com.shpun.mall.common.service.impl;

import com.shpun.mall.common.aop.RedisCache;
import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.mapper.MallFlashMapper;
import com.shpun.mall.common.model.MallFlash;
import com.shpun.mall.common.model.vo.MallFlashVo;
import com.shpun.mall.common.service.MallFlashService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:41
 */
@Service("mallFlashService")
public class MallFlashServiceImpl implements MallFlashService {

    @Autowired
    private MallFlashMapper flashMapper;

    @Override
    public void deleteByPrimaryKey(Integer flashId) {
        flashMapper.deleteByPrimaryKey(flashId);
    }

    @Override
    public void insertSelective(MallFlash record) {
        record.setCreateTime(new Date());
        splitStartTime(record);
        flashMapper.insertSelective(record);
    }

    @Override
    public MallFlash selectByPrimaryKey(Integer flashId) {
        return flashMapper.selectByPrimaryKey(flashId);
    }

    @Override
    public void updateByPrimaryKeySelective(MallFlash record) {
        record.setUpdateTime(new Date());
        splitStartTime(record);
        flashMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据start_time设值
     * @param flash
     */
    private void splitStartTime(MallFlash flash) {
        if (flash.getStartTime() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(flash.getStartTime());

            flash.setYear(calendar.get(Calendar.YEAR));
            flash.setMonth(calendar.get(Calendar.MONTH) + 1);
            flash.setDay(calendar.get(Calendar.DAY_OF_MONTH));
            flash.setHour(calendar.get(Calendar.HOUR_OF_DAY));
            flash.setMinute(calendar.get(Calendar.MINUTE));
        }
    }

    @RedisCache
    @Override
    public List<MallFlashVo> getTodayAvailableVoList() {
        List<MallFlashVo> flashVoList = flashMapper.getTodayAvailableVoList();
        if (CollectionUtils.isNotEmpty(flashVoList)) {
            for (Iterator<MallFlashVo> iterator = flashVoList.iterator(); iterator.hasNext();) {
                MallFlashVo flashVo = iterator.next();
                Integer hour = flashVo.getHour();
                Calendar now = Calendar.getInstance();

                // 小时为当前小时
                if (hour.equals(now.get(Calendar.HOUR_OF_DAY))) {
                    Integer minute = flashVo.getMinute();
                    Integer nowMinute = now.get(Calendar.MINUTE);
                    // 判断分钟是否在限时抢购范围内
                    if (nowMinute < minute || nowMinute >= minute + Const.DEFAULT_FLASH_LIMIT_MINS) {
                        iterator.remove();
                    }
                }
            }
        }
        return flashVoList;
    }

    @RedisCache
    @Override
    public List<MallFlashVo> getTodayVoList() {
        List<MallFlashVo> todayAvailableVoList = this.getTodayAvailableVoList();

        // 获取明天
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);
        List<MallFlashVo> tomorrowAvailableVoList = flashMapper.getTomorrowAvailableVoList(tomorrow.get(Calendar.YEAR),
                tomorrow.get(Calendar.MONTH) + 1, tomorrow.get(Calendar.DAY_OF_MONTH), tomorrow.get(Calendar.HOUR_OF_DAY));
        if (CollectionUtils.isNotEmpty(tomorrowAvailableVoList)) {
            todayAvailableVoList.addAll(tomorrowAvailableVoList);
        }

        if (CollectionUtils.isNotEmpty(todayAvailableVoList)) {
            for (MallFlashVo flashVo : todayAvailableVoList) {
                Date startTime = flashVo.getStartTime();
                flashVo.setEndTime(new Date(startTime.getTime() + Const.DEFAULT_FLASH_LIMIT_MINS * 60 * 1000));
            }
        }
        return todayAvailableVoList;
    }

    @Override
    public boolean isFlashing(Integer flashId) {
        return flashMapper.isFlashing(flashId, Const.DEFAULT_FLASH_LIMIT_MINS);
    }

    @Override
    public List<MallFlash> getList() {
        return flashMapper.getList();
    }
}
