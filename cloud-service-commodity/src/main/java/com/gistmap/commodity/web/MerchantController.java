package com.gistmap.commodity.web;

import com.gistmap.common.json.JsonResponseEntity;
import com.gistmap.commodity.persistence.domain.Merchant;
import com.gistmap.commodity.service.MerchantService;
import com.gistmap.commodity.service.dto.MerchantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zhangran
 * @date 2018/7/26
 */
@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @PostMapping
    public JsonResponseEntity save(@Valid @RequestBody MerchantDTO dto){
        merchantService.save(dto);
        return new JsonResponseEntity();
    }

    @GetMapping
    public JsonResponseEntity<Merchant> detail(String id) {
        JsonResponseEntity<Merchant> response = new JsonResponseEntity<>();
        response.data = merchantService.get(id);
        return response;
    }


}
