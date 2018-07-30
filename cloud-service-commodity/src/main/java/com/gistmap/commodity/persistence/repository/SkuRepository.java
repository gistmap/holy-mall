package com.gistmap.commodity.persistence.repository;

import com.gistmap.commodity.persistence.domain.Sku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author zhangran
 * @date 2018/7/26
 */
public interface SkuRepository extends JpaRepository<Sku, String>, JpaSpecificationExecutor<Sku> {

    long countByAttrId(Integer id);

}
