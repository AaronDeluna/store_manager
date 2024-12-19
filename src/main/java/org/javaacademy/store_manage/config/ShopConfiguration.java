package org.javaacademy.store_manage.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "shop")
public class ShopConfiguration {
    private List<String> status;
    private List<String> good;
}
