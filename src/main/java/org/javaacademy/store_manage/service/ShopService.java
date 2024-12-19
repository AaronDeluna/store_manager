package org.javaacademy.store_manage.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.javaacademy.store_manage.config.ShopConfiguration;
import org.javaacademy.store_manage.dto.ShopInfoDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopService {
    private static final String INVALID_RESPONSE_ERROR = "Ошибка: Запрос не выполнен успешно или тело ответа пустое.";
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ShopConfiguration shopConfiguration;

    public List<ShopInfoDto> getShopInfo() throws IOException {
        List<ShopInfoDto> responseStatusList = new ArrayList<>();
        for (String url : shopConfiguration.getStatus()) {
            Request request = buildRequest(url);

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful() || response.body() == null) {
                    throw new RuntimeException(INVALID_RESPONSE_ERROR);
                }
                String responseBody = response.body().string();
                ShopInfoDto shopInfoDto = extractShopInfo(responseBody);
                responseStatusList.add(shopInfoDto);
            }
        }

        return responseStatusList;
    }

    private Request buildRequest(String url) {
        return new Request.Builder()
                .url(url)
                .get()
                .build();
    }

    private ShopInfoDto extractShopInfo(String responseBody) throws JsonProcessingException {
        return objectMapper.readValue(responseBody, ShopInfoDto.class);
    }
}
