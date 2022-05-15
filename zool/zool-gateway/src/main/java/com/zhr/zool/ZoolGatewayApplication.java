package com.zhr.zool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZoolGatewayApplication {

    public static void main(String[] args) {

        SpringApplication.run(ZoolGatewayApplication.class, args);
    }

}
