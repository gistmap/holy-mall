package com.gistmap.order.service.remote;

import com.fasterxml.jackson.databind.JsonNode;
import com.gistmap.common.json.JsonConverter;
import com.gistmap.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhangran
 * @date 2018/8/3
 */
@Component
public class GoodsService {

    @Autowired
    private IGoodsService iGoodsService;

    public GoodsVO getGoods(String id){
        JsonNode jsonNode = iGoodsService.getGoods(id);
        if (jsonNode.get("data") != null) {
            String json = jsonNode.get("data").toString();
            GoodsVO goods = JsonConverter.toObject(json, GoodsVO.class);
            return goods;
        }
        return null;
    }

    public MerchantDTO getMerchant(String id) {
        JsonNode jsonNode = iGoodsService.getMerchant(id);
        if (jsonNode.get("data") != null) {
            String json = jsonNode.get("data").toString();
            MerchantDTO goods = JsonConverter.toObject(json, MerchantDTO.class);
            return goods;
        }
        return null;
    }
    public void operateStock(List<OrderService.GoodsForm> goodsForm) {
        for (OrderService.GoodsForm form : goodsForm) {
            iGoodsService.operateStock(form.id, form.count, false);
        }
    }
}
