package io.yunplusplus.service.api.coupon.dto;

public class CouponDto {

    /**
     * 用户名
     */
    private String username;

    /**
     * 消费券名称
     */
    private String couponName;

    /**
     * 消费券id
     */
    private String couponId;

    /**
     * 金额
     */
    private Integer denomination;

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

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public Integer getDenomination() {
        return denomination;
    }

    public void setDenomination(Integer denomination) {
        this.denomination = denomination;
    }

    @Override
    public String toString() {
        return "CouponDto{" +
                "username='" + username + '\'' +
                ", couponName='" + couponName + '\'' +
                ", couponId='" + couponId + '\'' +
                ", denomination=" + denomination +
                '}';
    }
}
