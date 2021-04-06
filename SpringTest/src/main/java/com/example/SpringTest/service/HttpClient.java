package com.example.SpringTest.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpClient {

    public String client(String url, HttpMethod method, MultiValueMap<String, String> params) {
        RestTemplate template = new RestTemplate();
        long userId = 32L;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("api-key", "43L04i8y4qEGNNxQ3DOv9neRMnM="); // 请求头部添加api-key
        ResponseEntity<String> responseEntity = template.exchange(url, HttpMethod.GET, new HttpEntity<String>(httpHeaders), String.class, userId);
        return responseEntity.getBody();
    }
}
