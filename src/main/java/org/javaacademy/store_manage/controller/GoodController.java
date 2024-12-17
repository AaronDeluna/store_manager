package org.javaacademy.store_manage.controller;

import lombok.RequiredArgsConstructor;
import org.javaacademy.store_manage.dto.GoodUpdatePriceDto;
import org.javaacademy.store_manage.service.UpdateGoodPriceService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class GoodController {
    private final UpdateGoodPriceService updateGoodPriceService;
    @PatchMapping("/good")
    public void updateGoodPrice(@RequestBody GoodUpdatePriceDto updatePriceDto) {
        updateGoodPriceService.updateProductPrice(updatePriceDto);
    }
}
