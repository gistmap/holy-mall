package com.gistmap.commodity.service;

import com.gistmap.commodity.persistence.domain.Attribute;
import com.gistmap.commodity.persistence.domain.Category;
import com.gistmap.common.exception.BaseException;
import com.gistmap.commodity.persistence.domain.Merchant;
import com.gistmap.commodity.persistence.enumeration.ReviewStatus;
import com.gistmap.commodity.persistence.repository.MerchantRepository;
import com.gistmap.commodity.service.dto.MerchantDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author zhangran
 * @date 2018/7/26
 */
@Component
public class MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;
    @Autowired
    private CategoryService categoryService;

    @Transactional(rollbackFor = BaseException.class)
    public void save(MerchantDTO dto) {
        Merchant merchant = new Merchant();
        BeanUtils.copyProperties(dto, merchant);
        merchant.setActive(false);
        merchant.setStatus(ReviewStatus.LOCK);
        merchant.setCategory(categoryService.get(dto.categoryId));
        merchantRepository.save(merchant);
    }

    public Merchant get(String id) {
        Optional<Merchant> byId = merchantRepository.findById(id);
        return byId.orElseThrow( () ->new BaseException("该商户不存在!"));

    }

    public List<Attribute> attrs(String id) {
        Merchant merchant = get(id);
        Category category = merchant.getCategory();
        return category.getAttributes();
    }
}
