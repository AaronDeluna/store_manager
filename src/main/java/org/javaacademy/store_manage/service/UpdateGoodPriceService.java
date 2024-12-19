package org.javaacademy.store_manage.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.javaacademy.store_manage.config.ShopConfiguration;
import org.javaacademy.store_manage.dto.GoodUpdatePriceDto;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UpdateGoodPriceService {
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ShopConfiguration shopConfiguration;

    public void updateProductPrice(GoodUpdatePriceDto updatePriceDto) throws IOException {
        for (String api : shopConfiguration.getGood()) {
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
