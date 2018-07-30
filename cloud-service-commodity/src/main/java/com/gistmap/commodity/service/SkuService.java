package com.gistmap.commodity.service;

import com.gistmap.commodity.persistence.repository.SkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhangran
 * @date 2018/7/26
 */
@Component
public class SkuService {

    @Autowired
    private SkuRepository skuRepository;

}
