package com.gistmap.commodity.persistence.repository;

import com.gistmap.commodity.persistence.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author zhangran
 * @date 2018/7/27
 */
public interface CategoryRepository extends JpaRepository<Category, Integer>, PagingAndSortingRepository<Category, Integer> {
}
