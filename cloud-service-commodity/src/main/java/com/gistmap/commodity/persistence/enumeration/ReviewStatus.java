package com.gistmap.commodity.persistence.enumeration;

/**
 * @author zhangran
 * @date 2018/7/26
 */
public enum ReviewStatus {
    /**
     * 未审核
     */
    LOCK,
    /**
     * 审核中
     */
    REVIEWING,
    /**
     * 拒绝
     */
    REFUSE,
    /**
     * 通过
     */
    PASS;
}
