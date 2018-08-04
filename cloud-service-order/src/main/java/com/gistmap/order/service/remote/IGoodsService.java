package com.gistmap.order.service.remote;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhangran
 * @date 2018/8/3
 */
@FeignClient("goods")
public interface IGoodsService {

    @RequestMapping("/goods/{id}")
    JsonNode getGoods(@PathVariable("id") String id);

    @RequestMapping("/merchant/{id}")
    JsonNode getMerchant(@PathVariable("id") String id);

    @RequestMapping("/operate/stock")
    JsonNode operateStock(@RequestParam String id, @RequestParam Integer count, @RequestParam boolean add);
}
