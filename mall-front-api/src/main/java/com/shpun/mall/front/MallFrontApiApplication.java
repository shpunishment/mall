package com.shpun.mall.front;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.shpun.mall.common.mapper")
@ComponentScan("com.shpun.mall")
public class MallFrontApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallFrontApiApplication.class, args);
    }

}
