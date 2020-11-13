package com.example.iot.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

@Configuration
public class ActiveMQConfig {

    @Autowired
    private MesIotProperties properties;

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(properties.getQueueName());
    }
}
