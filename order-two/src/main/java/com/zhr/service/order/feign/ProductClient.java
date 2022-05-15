package com.zhr.service.order.feign;

import com.zhr.service.order.fallback.ProductClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品服务客户端
 * name = "product-service"是服务端名称
 * @author zhr
 */
@FeignClient(name = "product-service",fallback = ProductClientFallback.class)
public interface ProductClient {

    //这样组合就相当于http://product-service/api/v1/product/find
    @GetMapping("/api/v1/product/find")
    String findById(@RequestParam(value = "id") int id);

}
