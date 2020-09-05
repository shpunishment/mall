package com.shpun.mall.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 用户收货地址
 * @Author: sun
 * @Date: 2020/8/22 10:34
 */
@ApiModel
public class MallUserAddress implements Serializable {

    @NotNull(groups = Update.class)
    @Min(value = 1, groups = Update.class)
    @Max(value = 2147483647, groups = Update.class)
    @ApiModelProperty("收货地址id")
    private Integer addressId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("用户id")
    private Integer userId;

    @NotBlank(groups = { Add.class, Update.class })
    @Length(min = 4, max = 20, groups = { Add.class, Update.class })
    @ApiModelProperty("收货人")
    private String name;

    @Min(value = 1, groups = { Add.class, Update.class })
    @Max(value = 2, groups = { Add.class, Update.class })
    @ApiModelProperty("性别，0无，1先生，2女士")
    private Integer sex;

    @NotBlank(groups = { Add.class, Update.class })
    @Length(min = 8, max = 15, groups = { Add.class, Update.class })
    @ApiModelProperty("收货电话")
    private String phone;

    @NotBlank(groups = { Add.class, Update.class })
    @Length(min = 5, max = 100, groups = { Add.class, Update.class })
    @ApiModelProperty("收货地址")
    private String address;

    @Min(value = 1, groups = { Add.class, Update.class })
    @Max(value = 5, groups = { Add.class, Update.class })
    @ApiModelProperty("地址类型，0未选择，1家，2父母家，3朋友家，4公司，5学校")
    private Integer type;

    @ApiModelProperty("删除标识，0未删除，1删除")
    private Integer deleted;

    private static final long serialVersionUID = 1L;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", addressId=").append(addressId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", userId=").append(userId);
        sb.append(", name=").append(name);
        sb.append(", sex=").append(sex);
        sb.append(", phone=").append(phone);
        sb.append(", address=").append(address);
        sb.append(", type=").append(type);
        sb.append(", deleted=").append(deleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 用户地址新增校验分组
     */
    public interface Add {
    }

    /**
     * 用户地址更新校验分组
     */
    public interface Update {
    }

}