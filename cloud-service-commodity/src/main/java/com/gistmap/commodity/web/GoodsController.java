package com.gistmap.commodity.web;

import com.gistmap.commodity.service.GoodsService;
import com.gistmap.commodity.service.dto.GoodsDTO;
import com.gistmap.commodity.service.vo.CategoryGoodsVO;
import com.gistmap.commodity.service.vo.GoodsVO;
import com.gistmap.common.json.JsonListResponseEntity;
import com.gistmap.common.json.JsonResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zhangran
 * @date 2018/7/26
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping
    public JsonResponseEntity save(@Valid @RequestBody GoodsDTO dto){
        goodsService.save(dto);
        return new JsonResponseEntity();
    }

    /**
     * 后台商品管理列表
     * @param pageable
     * @param name
     * @param merchantName
     * @return
     */
    @GetMapping("/list")
    public Page<GoodsVO> list(Pageable pageable,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false, name = "merchant_name") String merchantName){
        return goodsService.list(name, merchantName, pageable);
    }

    /**
     * 商品详情
     * @param id
     * @return GoodsVO
     */
    @GetMapping("/{id}")
    public JsonResponseEntity<GoodsVO> detail(@PathVariable("id") String id){
        JsonResponseEntity<GoodsVO> response = new JsonResponseEntity<>();
        response.data = goodsService.detail(id);
        return response;
    }

    /**
     * 首页分类商品
     * @return CategoryGoodsVO
     */
    @GetMapping
    public JsonListResponseEntity<CategoryGoodsVO> list(){
        JsonListResponseEntity<CategoryGoodsVO> response = new JsonListResponseEntity<>();
        response.setContent(goodsService.list());
        return response;
    }

    /**
     * 某商户下所有商品
     * @param merchantId
     * @return
     */
    @GetMapping("/merchant/{merchant_id}")
    public JsonListResponseEntity<GoodsVO> list(@PathVariable("merchant_id") String merchantId){
        JsonListResponseEntity<GoodsVO> response = new JsonListResponseEntity<>();
        response.setContent(goodsService.list(merchantId));
        return response;
    }

    @GetMapping("/operate/stock")
    public JsonResponseEntity operateStock(@RequestParam String id, @RequestParam Integer count,
                                           @RequestParam(required = false, defaultValue = "true") boolean add){
        goodsService.operateStock(id, count, add);
        return new JsonResponseEntity();
    }
}
