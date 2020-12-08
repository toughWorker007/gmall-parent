package com.atguigu.gmall.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @desc
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients("com.atguigu.gmall")
@ComponentScan(basePackages = {"com.atguigu.gmall"})
public class ServiceItemApp {
    public static void main(String[] args) {
        SpringApplication.run(ServiceItemApp.class,args);
    }
}
