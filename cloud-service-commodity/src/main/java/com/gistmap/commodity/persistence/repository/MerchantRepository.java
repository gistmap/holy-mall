package com.gistmap.commodity.persistence.repository;

import com.gistmap.commodity.persistence.domain.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author zhangran
 * @date 2018/7/26
 */
public interface MerchantRepository extends JpaRepository<Merchant, String>, JpaSpecificationExecutor<Merchant> {
}
