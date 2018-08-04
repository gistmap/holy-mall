package com.gistmap.commodity.service;

import com.gistmap.commodity.persistence.domain.Category;
import com.gistmap.commodity.persistence.domain.Merchant;
import com.gistmap.commodity.persistence.repository.CategoryRepository;
import com.gistmap.commodity.service.vo.CategoryGoodsVO;
import com.gistmap.commodity.service.vo.GoodsVO;
import com.gistmap.common.exception.BaseException;
import com.gistmap.commodity.persistence.domain.Goods;
import com.gistmap.commodity.persistence.domain.Sku;
import com.gistmap.commodity.persistence.repository.GoodsRepository;
import com.gistmap.commodity.service.dto.GoodsDTO;
import com.gistmap.commodity.service.dto.SkuDTO;
import com.github.wenhao.jpa.Sorts;
import com.github.wenhao.jpa.Specifications;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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

    @Autowired
    private CategoryRepository categoryRepository;

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

    private List<Sku> formSkus(List<SkuDTO> dtos){
        List<Sku> skus = Lists.newArrayList();
        for (SkuDTO dto : dtos) {
            Sku sku = new Sku();
            sku.setAttrId(dto.attrId);
            sku.setValue(dto.value);
            skus.add(sku);
        }
        return skus;
    }


    public Page<GoodsVO> list(String name, String merchantName, Pageable pageable) {
        return findAll(name, merchantName, pageable).map(GoodsVO::new);
    }

    private Page<Goods> findAll(String name, String merchantName, Pageable pageable) {
        Specification<Goods> specification = Specifications.<Goods>and()
                .eq((StringUtils.isNotBlank(name)), "name", name)
                .eq(StringUtils.isNotBlank(merchantName), "merchant.name", merchantName)
                .build();
        Sort sort = Sorts.builder()
                .desc( "createTime")
                .build();
        return goodsRepository.findAll(specification,  PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(), sort));
    }


    public GoodsVO detail(String id){
        return new GoodsVO(get(id));
    }

    @Transactional
    public List<CategoryGoodsVO>  list(){
        List<CategoryGoodsVO> result = Lists.newArrayList();
        List<Category> categories = categoryRepository.findAll();
        categories.parallelStream().forEach( c -> {
            List<GoodsVO> goods = Lists.newArrayList();
            c.getMerchants().parallelStream().forEach(
                    m -> goods.addAll(m.getGoods().parallelStream().map(GoodsVO::new).collect(toList()))
            );
            result.add(new CategoryGoodsVO(c.getName(), goods));
        });
        return result;
    }

    @Transactional
    public List<GoodsVO> list(String merchantId){
        Merchant merchant = merchantService.get(merchantId);
        return merchant.getGoods().stream().map(GoodsVO::new).collect(toList());
    }

    @Transactional(rollbackFor = BaseException.class, isolation = Isolation.READ_COMMITTED)
    public void operateStock(String id, Integer count, boolean add) {
        int latestCount = -1;
        Goods goods = get(id);
        if (add) {
            latestCount = goods.getStock() + count;
        } else {
            if (count > goods.getStock()) {
                throw new BaseException("库存不能小于0");
            }
            latestCount = goods.getStock() - count;
        }
        goods.setStock(latestCount);
        goodsRepository.save(goods);
    }
}
