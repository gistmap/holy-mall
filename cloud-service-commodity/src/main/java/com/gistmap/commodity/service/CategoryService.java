package com.gistmap.commodity.service;

import com.gistmap.common.exception.BaseException;
import com.gistmap.commodity.persistence.domain.Category;
import com.gistmap.commodity.persistence.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


/**
 * @author zhangran
 * @date 2018/7/27
 */
@Component
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void save(Category category){
        categoryRepository.save(category);
    }

    public void remove(String categoryIds){
        String [] ids = categoryIds.split(",");
        for (String id : ids) {
            Category category = get(Integer.parseInt(id));
            if (category.getMerchants().size() > 0) {
                throw new BaseException("此分类下有商户关联，不可删除");
            }
            if (category.getAttributes().size() > 0) {
                throw new BaseException("此分类下有属性关联，不可删除");
            }
            categoryRepository.delete(category);
        }
    }

    public Category get(Integer id) {
        return categoryRepository.findById(id).orElseThrow(()-> new BaseException("不存在的分类"));
    }

    public Page<Category> list(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }
}
