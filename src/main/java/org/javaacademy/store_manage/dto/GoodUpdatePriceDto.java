package org.javaacademy.store_manage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodUpdatePriceDto {
    @Schema(description = "Имя")
    private String name;
    @Schema(description = "Новая цена")
    @JsonProperty("new_price")
    private BigDecimal newPrice;
}
