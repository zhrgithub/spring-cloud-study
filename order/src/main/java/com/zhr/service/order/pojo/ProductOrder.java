package com.zhr.service.order.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zhr_java@163.com
 * @date 2022/5/14 18:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrder {

    /**
     * 订单ID
     */
    private int id;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 订单号
     */
    private String tradeNo;

    /**
     * 价格,分
     */
    private int price;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 用户id
     */
    private int userId;

    /**
     * 用户名
     */
    private String userName;

}
