package io.yunplusplus.coupon.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import io.yunplusplus.coupon.dao.CouponDao;
import io.yunplusplus.coupon.model.Coupon;
import io.yunplusplus.service.api.coupon.dto.CouponDto;
import io.yunplusplus.service.api.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author icefox
 */
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponDao couponDao;

    @Override
    public void saveUserCoupon(CouponDto couponDto) {
        Coupon coupon = new Coupon();
        copy(couponDto, coupon);
        coupon.setId(IdWorker.getIdStr());
        try {
            couponDao.insert(coupon);
            System.out.printf("%s获得消费券(%s),金额大小:%d", couponDto.getUsername(), couponDto.getCouponName(), couponDto.getDenomination());
        } catch (Exception e) {
            System.out.printf("%s已经获得消费券(%s),金额大小:%d", couponDto.getUsername(), couponDto.getCouponName(), couponDto.getDenomination());
        }
    }

    public static void copy(CouponDto couponDto, Coupon coupon) {
        coupon.setCouponName(couponDto.getCouponName());
        coupon.setCouponId(couponDto.getCouponId());
        coupon.setUsername(couponDto.getUsername());
        coupon.setDenomination(couponDto.getDenomination());
    }
}
