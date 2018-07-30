package com.gistmap.commodity.web;

import com.gistmap.common.json.JsonResponseEntity;
import com.gistmap.commodity.persistence.domain.Attribute;
import com.gistmap.commodity.service.AttributeService;
import com.gistmap.commodity.service.dto.AttributeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangran
 * @date 2018/7/27
 */
@RestController
@RequestMapping("/attr")
public class AttributeController {

    @Autowired
    AttributeService attributeService;

    @PostMapping
    public JsonResponseEntity save(@RequestBody AttributeDTO dto){
        attributeService.save(dto);
        return new JsonResponseEntity();
    }

    @DeleteMapping
    public JsonResponseEntity remove(String ids){
        attributeService.remove(ids);
        return new JsonResponseEntity();
    }

    @GetMapping("/list")
    public Page<Attribute> list(Pageable pageable){
        return attributeService.list(pageable);
    }
}
