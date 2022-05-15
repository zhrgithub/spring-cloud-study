package com.zhr.service.produce.service;

import com.zhr.service.produce.pojo.Product;

import java.util.List;

/**
 * @author zhr_java@163.com
 * @date 2022/5/14 18:15
 */
public interface ProductService {

  /**
   * 查找所有商品
   *
   * @return
   */
  List<Product> listProduct();

  /**
   * 根据商品ID查找商品
   *
   * @param id
   * @return
   */
  Product findById(int id);
}
