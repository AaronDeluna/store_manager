package org.javaacademy.store_manage.controller;

import lombok.RequiredArgsConstructor;
import org.javaacademy.store_manage.service.ShopService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;
    @GetMapping("/status")
    public List<String> grtShopInfo() throws IOException {
        return shopService.getShopInfo();
    }
}
