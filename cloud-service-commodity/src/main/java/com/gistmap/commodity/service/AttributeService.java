package com.gistmap.commodity.service;

import com.gistmap.common.exception.BaseException;
import com.gistmap.commodity.persistence.domain.Attribute;
import com.gistmap.commodity.persistence.repository.AttributeRepository;
import com.gistmap.commodity.persistence.repository.SkuRepository;
import com.gistmap.commodity.service.dto.AttributeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * @author zhangran
 * @date 2018/7/26
 */
@Component
public class AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private SkuRepository skuRepository;
    @Autowired
    private CategoryService categoryService;

    public Integer save(AttributeDTO dto){
        Attribute attribute = new Attribute();
        attribute.setName(dto.name);
        attribute.setCategory(categoryService.get(dto.category_id));
        attributeRepository.save(attribute);
        return attribute.getId();
    }

    public void remove(String attrIds) {
        String [] ids = attrIds.split(",");
        for (String id : ids) {
            if (skuRepository.countByAttrId(Integer.parseInt(id)) > 0) {
                throw new BaseException("此分类下有商户关联，不可删除");
            }
            attributeRepository.deleteById(Integer.parseInt(id));
        }
    }

    public Attribute get(Integer id){
        return attributeRepository.findById(id).orElseThrow(() -> new BaseException("不存在的属性"));
    }

    public Page<Attribute> list(Pageable pageable) {
        return attributeRepository.findAll(pageable);
    }
}
