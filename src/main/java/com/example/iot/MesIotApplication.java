package com.example.iot;

import com.example.iot.config.MesIotProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan(value = "com.example.iot.mapper")
@EnableJms
@EnableConfigurationProperties({MesIotProperties.class})
public class MesIotApplication {

    public static void main(String[] args) {
        SpringApplication.run(MesIotApplication.class, args);
    }

}
