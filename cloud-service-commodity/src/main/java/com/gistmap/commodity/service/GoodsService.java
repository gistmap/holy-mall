package com.gistmap.commodity.service;

import com.gistmap.common.exception.BaseException;
import com.gistmap.commodity.persistence.domain.Goods;
import com.gistmap.commodity.persistence.domain.Sku;
import com.gistmap.commodity.persistence.repository.GoodsRepository;
import com.gistmap.commodity.service.dto.GoodsDTO;
import com.gistmap.commodity.service.dto.SkuDTO;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhangran
 * @date 2018/7/26
 */
@Component
public class GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private MerchantService merchantService;

    /**
     *
     * @param dto
     */
    public void save(GoodsDTO dto) {
        Goods goods = new Goods();
        goods.setName(dto.name);
        goods.setDescription(dto.description);
        goods.setIcon(dto.icon);
        goods.setImage(dto.image);
        goods.setPrice(dto.price);
        goods.setDiscount(dto.discount);
        goods.setStartTime(dto.startTime);
        goods.setExpirationTime(dto.expirationTime);
        goods.setStock(dto.stock);
        goods.setMerchant(merchantService.get(dto.merchantId));
        goods.getSkus().clear();
        goods.setTotal( dto.stock);
        List<Sku> skus = formSkus(dto.skus);
        skus.forEach(goods::addSku);
        goodsRepository.save(goods);
    }

    public Goods get(String id){
        return goodsRepository.findById(id).orElseThrow(() -> new BaseException("不存在的商品"));
    }

    public List<Sku> formSkus(List<SkuDTO> dtos){
        List<Sku> skus = Lists.newArrayList();
        for (SkuDTO dto : dtos) {
            Sku sku = new Sku();
            sku.setAttrId(dto.attrId);
            sku.setValue(dto.value);
            skus.add(sku);
        }
        return skus;
    }


}
