package org.javaacademy.store_manage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodUpdatePriceDto {
    private String name;
    @JsonProperty("new_price")
    private BigDecimal newPrice;
}
