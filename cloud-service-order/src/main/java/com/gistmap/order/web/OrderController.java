package com.gistmap.order.web;

import com.gistmap.common.json.JsonResponseEntity;
import com.gistmap.order.service.OrderService;
import com.gistmap.order.service.dto.MerchantOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangran
 * @date 2018/8/3
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public JsonResponseEntity<MerchantOrderDTO> createOrder(@RequestBody OrderService.OrderForm form){
        JsonResponseEntity<MerchantOrderDTO> response = new JsonResponseEntity<>();
        response.data = orderService.createOrder(form);
        return response;
    }
}
