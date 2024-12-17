package org.javaacademy.store_manage.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.jsonpath.JsonPath;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.javaacademy.store_manage.dto.ShopInfoDto;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ShopService {
    private static final String INVALID_RESPONSE_ERROR = "Ошибка: Запрос не выполнен успешно или тело ответа пустое.";
    @Value("${shop.first.api}")
    private String firstUrl;
    @Value("${shop.second.api}")
    private String secondUrl;
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public List<String> getShopInfo() {
        List<String> responseStatusList = new ArrayList<>();
        for (String api : List.of(firstUrl, secondUrl)) {
            Request request = buildRequest(api);

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful() || response.body() == null) {
                    throw new RuntimeException(INVALID_RESPONSE_ERROR);
                }
                responseStatusList.add(extractShopInfoResponse(response));
            }
        }

        return responseStatusList;
    }

    private Request buildRequest(String api) {
        return new Request.Builder()
                .url(api)
                .get()
                .build();
    }

    public String extractShopInfoResponse(Response response) throws IOException {
        String responseBody = response.body().string();
        String name = JsonPath.parse(responseBody).read("$.name", String.class);
        String shopStatus = JsonPath.parse(responseBody).read("$.shopStatus", String.class);
        String timeOpen = JsonPath.parse(responseBody).read("$.time_open", String.class);
        String timeClose = JsonPath.parse(responseBody).read("$.time_close", String.class);
        ShopInfoDto shopInfoDto = new ShopInfoDto(name, shopStatus, timeOpen, timeClose);
        return objectMapper.writeValueAsString(shopInfoDto);
    }
}
