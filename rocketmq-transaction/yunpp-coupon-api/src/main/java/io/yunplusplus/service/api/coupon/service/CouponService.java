package io.yunplusplus.service.api.coupon.service;

import io.yunplusplus.service.api.coupon.dto.CouponDto;

/**
 * 消费券服务
 *
 * @author icefox
 */
public interface CouponService {

    /**
     * 保存用户消费券
     *
     * @param couponDto couponDto
     */
    void saveUserCoupon(CouponDto couponDto);
}
