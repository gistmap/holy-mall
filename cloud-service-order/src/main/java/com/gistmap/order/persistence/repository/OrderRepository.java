package com.gistmap.order.persistence.repository;

import com.gistmap.order.persistence.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhangran
 * @date 2018/8/3
 */
public interface OrderRepository extends JpaRepository<Order, String> {
}
