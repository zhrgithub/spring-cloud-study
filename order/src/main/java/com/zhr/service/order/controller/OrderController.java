package com.zhr.service.order.controller;

import com.zhr.service.order.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author zhr_java@163.com
 * @date 2022/5/14 18:30
 */
@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    /**
     * 通过Rest API远程调用
     */
    @Autowired
    private ProductOrderService productOrderService;



    @RequestMapping("save")
    public Object save(@RequestParam("user_id")int userId, @RequestParam("product_id") int productId){

        return productOrderService.save(userId, productId);
    }






}
