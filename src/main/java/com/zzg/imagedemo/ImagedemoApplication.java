package com.zzg.imagedemo;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zzg.imagedemo.mapper")
public class ImagedemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImagedemoApplication.class, args);
    }

}
