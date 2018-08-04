package com.gistmap.order.persistence.domain;

/**
 * @author zhangran
 * @date 2018/8/3
 */
public enum OrderStatus {
    /**
     * 未支付
     */
    NOTPAY,
    /**
     * 已支付
     */
    PAID,
    /**
     * 已完成
     */
    COMPLETE,
    /**
     * 自动取消
     */
    EXPIRED,
    /**
     * 待退款
     */
    APPLYREFUND,
    /**
     * 已退款
     */
    REFUNDED,
    /**
     * 手动取消
     */
    CANCEL,
    /**
     * 拒绝退款
     */
    REFUSE,
    /**
     * 配送中
     */
    DISTRIBUTION
}
