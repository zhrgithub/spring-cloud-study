package com.zhr.service.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhr.service.order.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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

    /**
     *HystrixCommand的属性查看HystrixCommandProperties类
     * @param userId
     * @param productId
     * @return
     */
    @RequestMapping("save")
    @HystrixCommand(fallbackMethod = "saveOrderFail",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")})
    public Object save(@RequestParam("user_id")int userId, @RequestParam("product_id") int productId ){

        return productOrderService.save(userId, productId);
    }


    //注意，方法签名一定要要和api方法一致
    private Object saveOrderFail(int userId, int productId){

        System.out.println("controller中的降级方法");

        Map<String, Object> msg = new HashMap<>();
        msg.put("code", -1);
        msg.put("msg", "抢购人数太多，您被挤出来了，稍等重试");
        return msg;
    }


    @RequestMapping("list")
    public void list(@RequestParam("user_id")int userId, @RequestParam("product_id") int productId, HttpServletRequest request){
        String token = request.getHeader("token");
        String cookie = request.getHeader("cookie");
        //会发现token值能够获取，cookie无法获取，原因是因为网关会过滤掉敏感词
        System.out.println("token="+token);
        System.out.println("cookie="+cookie);
    }



}
