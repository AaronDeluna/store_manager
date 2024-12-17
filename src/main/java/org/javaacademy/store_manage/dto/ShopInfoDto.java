package org.javaacademy.store_manage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopInfoDto {
    private String name;
    private String shopStatus;
    private String timeOpen;
    private String timeClose;

}
