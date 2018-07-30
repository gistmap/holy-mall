package com.gistmap.commodity.web;

import com.gistmap.common.json.JsonResponseEntity;
import com.gistmap.commodity.persistence.domain.Category;
import com.gistmap.commodity.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangran
 * @date 2018/7/27
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public JsonResponseEntity save(@RequestBody Category category){
        categoryService.save(category);
        return new JsonResponseEntity();
    }

    @DeleteMapping
    public JsonResponseEntity remove(String ids){
        categoryService.remove(ids);
        return new JsonResponseEntity();
    }

    @GetMapping("/list")
    public Page<Category> list(Pageable pageable){
        return categoryService.list(pageable);
    }


}
