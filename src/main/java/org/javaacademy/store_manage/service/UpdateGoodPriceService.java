package org.javaacademy.store_manage.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.javaacademy.store_manage.dto.GoodUpdatePriceDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateGoodPriceService {
    @Value("${shop.first-good.api}")
    private String firstGood;
    @Value("${shop.second-good.api}")
    private String secondGood;
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public void updateProductPrice(GoodUpdatePriceDto updatePriceDto) {
        for (String api : List.of(firstGood, secondGood)) {
            RequestBody requestBody = RequestBody.create(
                    objectMapper.writeValueAsBytes(updatePriceDto)
            );
            Request request = new Request.Builder()
                    .url(api)
                    .patch(requestBody)
                    .addHeader("Content-Type", "application/json")
                    .build();

            client.newCall(request).execute();
        }
    }
}
