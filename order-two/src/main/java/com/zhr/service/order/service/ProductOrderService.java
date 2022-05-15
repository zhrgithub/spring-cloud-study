package com.zhr.service.order.service;

import com.zhr.service.order.pojo.ProductOrder;

/**
 * @author zhr_java@163.com
 * @date 2022/5/14 18:24
 */
public interface ProductOrderService {

    /**
     * 下单接口
     * @param userId
     * @param productId
     * @return
     */
    ProductOrder save(int userId, int productId);

}
