package com.shpun.mall.common.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/23 18:12
 */
@ApiModel
public class MallClassifyVo implements Serializable {

    @ApiModelProperty("商品分类id")
    private Integer classifyId;

    @ApiModelProperty("分类图片，为文件id")
    private Integer pic;

    @ApiModelProperty("分类名")
    private String classifyName;

    @ApiModelProperty("父分类id")
    private Integer pid;

    @ApiModelProperty("分类id")
    private Integer id;

    @ApiModelProperty("分类名")
    private String text;

    @ApiModelProperty("子分类")
    private List<MallClassifyVo> children;

    private static final long serialVersionUID = 1L;

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public Integer getPic() {
        return pic;
    }

    public void setPic(Integer pic) {
        this.pic = pic;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<MallClassifyVo> getChildren() {
        return children;
    }

    public void setChildren(List<MallClassifyVo> children) {
        this.children = children;
    }
}
