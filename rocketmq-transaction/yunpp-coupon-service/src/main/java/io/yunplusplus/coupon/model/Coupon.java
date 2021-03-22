package io.yunplusplus.coupon.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 消费券
 *
 * @author icefox
 */
@TableName("ypp_coupon")
public class Coupon implements Serializable {

    /**
     * 主键id
     */
    @TableId("id")
    private String id;
    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 消费券名称
     */
    @TableField("coupon_name")
    private String couponName;

    /**
     * 消费券id
     */
    @TableField("coupon_id")
    private String couponId;

    /**
     * 金额
     */
    @TableField("denomination")
    private Integer denomination;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private String createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Integer getDenomination() {
        return denomination;
    }

    public void setDenomination(Integer denomination) {
        this.denomination = denomination;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", couponName='" + couponName + '\'' +
                ", couponId='" + couponId + '\'' +
                ", denomination=" + denomination +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
