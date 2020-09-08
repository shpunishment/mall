package com.shpun.mall.common.service.impl;

import com.shpun.mall.common.aop.RedisCache;
import com.shpun.mall.common.mapper.MallClassifyMapper;
import com.shpun.mall.common.model.MallClassify;
import com.shpun.mall.common.model.vo.MallClassifyVo;
import com.shpun.mall.common.service.MallClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:40
 */
@Service("mallClassifyService")
public class MallClassifyServiceImpl implements MallClassifyService {

    @Autowired
    private MallClassifyMapper classifyMapper;

    @Override
    public void deleteByPrimaryKey(Integer classifyId) {
        List<Integer> classifyIdList = new ArrayList<>();
        MallClassify classify = this.selectByPrimaryKey(classifyId);
        if (classify.getPid() == 0) {
            // 获取分类下的id，一并删除
            List<Integer> childrenClassifyId = this.getClassifyIdByPid(classifyId);
            classifyIdList.addAll(childrenClassifyId);
        }
        classifyIdList.add(classifyId);

        classifyMapper.deleteBatch(classifyIdList);
    }

    @Override
    public void insertSelective(MallClassify record) {
        record.setCreateTime(new Date());
        Integer maxSn = classifyMapper.getMaxSnByPid(record.getPid());
        record.setSn(maxSn == null ? 1 : maxSn + 1);
        classifyMapper.insertSelective(record);
    }

    @Override
    public MallClassify selectByPrimaryKey(Integer classifyId) {
        return classifyMapper.selectByPrimaryKey(classifyId);
    }

    @Override
    public void updateByPrimaryKeySelective(MallClassify record) {
        record.setUpdateTime(new Date());
        classifyMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void up(Integer classifyId) {
        MallClassify classify = selectByPrimaryKey(classifyId);
        MallClassify prev = classifyMapper.getPrev(classify);
        if (prev != null) {
            this.exchangeSn(prev, classify);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void down(Integer classifyId) {
        MallClassify classify = selectByPrimaryKey(classifyId);
        MallClassify next = classifyMapper.getNext(classify);
        if (next != null) {
            this.exchangeSn(next, classify);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void top(Integer classifyId) {
        MallClassify classify = selectByPrimaryKey(classifyId);
        Integer topSn = classifyMapper.getMinSnByPid(classify.getPid());
        classifyMapper.goNext(classify.getPid(), topSn, classify.getSn());

        classify.setSn(topSn);
        this.updateByPrimaryKeySelective(classify);
    }

    /**
     * 交换两个分类的排序号
     * @param oldClassify
     * @param newClassify
     */
    private void exchangeSn(MallClassify oldClassify, MallClassify newClassify) {
        Integer tempSn = oldClassify.getSn();
        oldClassify.setSn(newClassify.getSn());
        newClassify.setSn(tempSn);

        this.updateByPrimaryKeySelective(oldClassify);
        this.updateByPrimaryKeySelective(newClassify);
    }

    @Override
    public List<Integer> getClassifyIdByPid(Integer pid) {
        return classifyMapper.getClassifyIdByPid(pid);
    }

    @Override
    public List<MallClassify> getByPid(Integer pid) {
        return classifyMapper.getByPid(pid);
    }

    @RedisCache
    @Override
    public List<MallClassifyVo> getVoList() {
        List<MallClassifyVo> classifyVoList = new ArrayList<>();

        List<MallClassifyVo> allVoList = classifyMapper.getAllVo();
        allVoList.forEach(vo -> {
            // pid等于0为一级分类
            if (vo.getPid() == 0) {
                vo.setId(vo.getClassifyId());
                vo.setText(vo.getClassifyName());
                classifyVoList.add(vo);
            }
        });

        // 添加二级分类
        for(MallClassifyVo vo : classifyVoList){
            vo.setChildren(getChildrenVo(vo.getClassifyId(), allVoList));
        }

        return classifyVoList;
    }

    /**
     * 获取分类树vo的二级分类
     * @param realId
     * @param allVoList
     * @return
     */
    private List<MallClassifyVo> getChildrenVo(Integer realId, List<MallClassifyVo> allVoList) {
        List<MallClassifyVo> childrenList = new ArrayList<>();
        for(MallClassifyVo classifyVo : allVoList){
            if(classifyVo.getPid().equals(realId)){
                classifyVo.setId(classifyVo.getClassifyId());
                classifyVo.setText(classifyVo.getClassifyName());
                childrenList.add(classifyVo);
            }
        }
        return childrenList;
    }

    @RedisCache
    @Override
    public List<MallClassifyVo> getVoByPid(Integer pid) {
        return classifyMapper.getVoByPid(pid);
    }
}
