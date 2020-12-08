package com.atguigu.gmall.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @desc
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.atguigu.gmall"})
public class ProductApp {
    public static void main(String[] args) {
        SpringApplication.run(ProductApp.class,args);
    }
}
