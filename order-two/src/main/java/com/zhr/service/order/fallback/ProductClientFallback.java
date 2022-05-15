package com.zhr.service.order.fallback;

import com.zhr.service.order.feign.ProductClient;
import org.springframework.stereotype.Component;

/**
 * @author zhr_java@163.com
 * @date 2022/5/15 00:13
 */
@Component
public class ProductClientFallback implements ProductClient {

    @Override
    public String findById(int id) {
        System.out.println("ProductClientFallback中的降级方法");

        //这里对该接口进行一些逻辑降级处理........
        return null;
    }

}
