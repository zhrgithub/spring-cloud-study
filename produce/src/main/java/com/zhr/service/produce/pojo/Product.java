package com.zhr.service.produce.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhr_java@163.com
 * @date 2022/5/14 18:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

  /**
   * 产品ID
   */
  private int id;

  /**
   * 商品名称
   */
  private String name;

  /**
   * 价格,分为单位
   */
  private int price;

  /**
   * 库存
   */
  private int store;
}
