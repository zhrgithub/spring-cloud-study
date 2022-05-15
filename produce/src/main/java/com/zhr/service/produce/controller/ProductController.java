package com.zhr.service.produce.controller;

import com.zhr.service.produce.pojo.Product;
import com.zhr.service.produce.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author zhr_java@163.com
 * @date 2022/5/14 18:17
 */
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

  // 集群情况下，用于订单服务查看到底调用的是哪个商品微服务节点
  @Value("${server.port}")
  private String port;

  @Autowired private ProductService productService;

  // 获取所有商品列表
  @GetMapping("list")
  public Object list() {
    return productService.listProduct();
  }

  // 根据id查找商品详情
  @GetMapping("find")
  public Object findById(int id) throws InterruptedException {
    Product product = productService.findById(id);
    Product result = new Product();
    BeanUtils.copyProperties(product, result);
    result.setName(result.getName() + " data from port=" + port);
    // 睡眠两秒，微服务默认一秒就超时，所以会到降级方法
    //        TimeUnit.SECONDS.sleep(2);
    return result;
  }
}
