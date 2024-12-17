package org.javaacademy.store_manage.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.javaacademy.store_manage.dto.GoodUpdatePriceDto;
import org.javaacademy.store_manage.service.UpdateGoodPriceService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
@Tag(name = "Good Controller", description = "Контроллер для работы с продукцией")
public class GoodController {
    private final UpdateGoodPriceService updateGoodPriceService;
    @PatchMapping("/good")
    public void updateGoodPrice(@RequestBody GoodUpdatePriceDto updatePriceDto) throws IOException {
        updateGoodPriceService.updateProductPrice(updatePriceDto);
    }
}
