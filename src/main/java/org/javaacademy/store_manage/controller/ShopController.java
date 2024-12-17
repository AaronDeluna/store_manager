package org.javaacademy.store_manage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.javaacademy.store_manage.dto.ShopInfoDto;
import org.javaacademy.store_manage.service.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
@Tag(name = "Shop controller", description = "Контроллер для работы с магазинами")
public class ShopController {
    private final ShopService shopService;
    @GetMapping("/status")
    @Operation(summary = "Получение статуса магазинов", description = "Получение статуса работы магазинов")
    @ApiResponse(responseCode = "200 - Успешно",content = {
            @Content(mediaType = "plain/text",schema = @Schema(implementation = String.class)),
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ShopInfoDto.class)))
    })
    public ResponseEntity<?> grtShopInfo() throws IOException {
        if (shopService.getShopInfo().isEmpty()) {
            return ResponseEntity.ok("Нет данных о магазинах");
        }
        return ResponseEntity.ok(shopService.getShopInfo());
    }
}
