package com.gistmap.commodity.web;

import com.gistmap.common.json.JsonResponseEntity;
import com.gistmap.commodity.service.GoodsService;
import com.gistmap.commodity.service.dto.GoodsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @GetMapping
//    public JsonResponseEntity<> detail(String id) {
//
//    }

}
