package com.zhr.service.order.service.impl;

import com.zhr.service.order.pojo.ProductOrder;
import com.zhr.service.order.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author zhr_java@163.com
 * @date 2022/5/14 18:25
 */
@Service
public class ProductOrderServiceImpl implements ProductOrderService {


    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ProductOrder save(int userId, int productId) {
        //product-service是微服务名称（这里指向的商品微服务名称）,api/v1/product/find?id=? 就是商品微服务对外的接口
        Map<String, Object> productMap = restTemplate.getForObject("http://product-service/api/v1/product/find?id=" + productId, Map.class);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());
        //获取商品名称和商品价格
        productOrder.setProductName(productMap.get("name").toString());
        productOrder.setPrice(Integer.parseInt(productMap.get("price").toString()));

        //因为在商品微服务配置了集群，所以这里打印看下调用了是哪个集群节点，输出端口号。
        System.out.println(productMap.get("name").toString());
        return productOrder;
    }

}
