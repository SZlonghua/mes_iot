package com.example.iot.config;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "mes.iot"
)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MesIotProperties {
    private String queueName = "liaotao-test";
}
