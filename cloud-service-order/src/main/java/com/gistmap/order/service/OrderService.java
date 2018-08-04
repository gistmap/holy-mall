package com.gistmap.order.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gistmap.common.json.JsonConverter;
import com.gistmap.common.util.SerialGenerator;
import com.gistmap.order.persistence.domain.Order;
import com.gistmap.order.persistence.domain.OrderDetail;
import com.gistmap.order.persistence.domain.OrderStatus;
import com.gistmap.order.persistence.repository.OrderRepository;
import com.gistmap.order.service.dto.MerchantOrderDTO;
import com.gistmap.order.service.remote.GoodsService;
import com.gistmap.order.service.remote.GoodsVO;
import com.gistmap.order.service.remote.MerchantDTO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangran
 * @date 2018/8/3
 */
@Component
public class OrderService {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public MerchantOrderDTO createOrder(OrderForm form) {
        Date dt = new Date();
        TmpInfo tmpInfo = generateInfo(form);
        Order order = new Order();
        order.setId(generateOrderId(new DateTime(dt), form.merchantId));
        order.setUid(form.uid);
        order.setAmount(tmpInfo.sum);
        order.setAddressId(form.addressId);
        order.setStatus(OrderStatus.NOTPAY);
        order.setMerchantId(form.merchantId);
        order.setDiscount(tmpInfo.totalDiscount);
        order.setInfo(JsonConverter.toJson(tmpInfo.info.values()));

        List<OrderDetail> details = Lists.newArrayList();
        for (GoodsForm goodsForm : form.goodsForms) {
            ObjectNode on = tmpInfo.info.get(goodsForm.id);
            OrderDetail detail = new OrderDetail();
            detail.setId(SerialGenerator.randomUUID());
            detail.setOrderId(order.getId());
            detail.setCount(goodsForm.count);
            detail.setGoodsId(goodsForm.id);
            detail.setAmount((on.get("price").asInt() - on.get("discount").asInt()) * goodsForm.count);
            detail.setDiscount(on.get("discount").asInt() * goodsForm.count);
            details.add(detail);
        }
        order.setDetails(details);
        orderRepository.save(order);
        // todo 减库存
        goodsService.operateStock(form.goodsForms);
        // todo 清购物车
        // todo 未付款超时
        return buildDTO(order, details);
    }

    private TmpInfo generateInfo(OrderForm form) {
        List<GoodsForm> goodsForms = form.goodsForms;
        int sum = 0;
        int totalDiscount = 0;
        Map<String, ObjectNode> map = Maps.newHashMap();
        for (GoodsForm one : goodsForms) {
            GoodsVO goods = goodsService.getGoods(one.id);
            ObjectNode on = JsonConverter.newJson();
            on.put("name", goods.getName());
            on.put("image", goods.getImage());
            on.put("price", goods.getPrice());
            on.put("discount", goods.getDiscount());
            sum += one.count * (goods.getPrice() - goods.getDiscount());
            totalDiscount += one.count * goods.getDiscount();
            map.put(one.id, on);
        }
        return new TmpInfo(map, sum, totalDiscount);
    }

    private String generateOrderId(DateTime dt, String merchantId) {
        return dt.toString("yyyyMMddHHmmssSSS") + merchantId.substring(merchantId.length() - 10) +
                String.format("#%04d",generateSequence(merchantId));
    }

    private int generateSequence(String merchantId) {
            String key = "merchant:order:seq:" + merchantId + ":" + Days.daysBetween(new DateTime(0), DateTime.now()).getDays();
            Long incr = redisTemplate.opsForValue().increment(key,1L);
            if (incr == 1L) {
                redisTemplate.expire(key, 86400, TimeUnit.SECONDS);
            }
            return incr.intValue();
    }

    private MerchantOrderDTO buildDTO(Order order, List<OrderDetail> details) {
        MerchantOrderDTO dto = new MerchantOrderDTO();
        MerchantDTO merchant = goodsService.getMerchant(order.getMerchantId());
        return null;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class OrderForm {
        public String uid;
        public String merchantId;
        public String cartId;
        public String addressId;
        public List<GoodsForm> goodsForms;
    }

    public static class GoodsForm {
        public String id;
        public Integer count;
    }

    private static class TmpInfo {
        Map<String, ObjectNode> info;
        Integer sum;
        Integer totalDiscount;
        Integer couponDiscount;
        String couponId;

        TmpInfo(Map<String, ObjectNode> info, Integer sum, Integer totalDiscount) {
            this.info = info;
            this.sum = sum;
            this.totalDiscount = totalDiscount;
        }
    }
}
