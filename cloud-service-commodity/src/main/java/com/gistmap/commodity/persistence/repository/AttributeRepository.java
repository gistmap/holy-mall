package com.gistmap.commodity.persistence.repository;

import com.gistmap.commodity.persistence.domain.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author zhangran
 * @date 2018/7/26
 */
public interface AttributeRepository extends JpaRepository<Attribute, Integer>, JpaSpecificationExecutor<Attribute>,
        PagingAndSortingRepository<Attribute, Integer> {
}
