package com.gistmap.ddbeat.user.persistence.repository;

import com.gistmap.ddbeat.user.persistence.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author zhangran
 * @date 2018/7/9
 */
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    @Query("SELECT u FROM User u WHERE u.name=?1 or u.email=?1")
    User findByEmailOrName(String name);

    User findByEmail(String eamil);
}
