package com.gistmap.ddbeat.user.persistence.repository;

import com.gistmap.ddbeat.user.persistence.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author zhangran
 * @date 2018/8/2
 */
public interface AddressRepository extends JpaRepository<Address, String>, JpaSpecificationExecutor<Address> {
}
