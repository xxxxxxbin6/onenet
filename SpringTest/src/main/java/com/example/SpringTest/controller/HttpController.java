package com.example.SpringTest.controller;


import com.example.SpringTest.service.HttpClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * springboot控制类,添加请求的具体内容
 */
@RestController
@RequestMapping(value = "data") //映射的路径
public class HttpController {

    @Autowired
    HttpClient httpClient;

    @RequestMapping(value = "/concentration") // 映射的路径
    public String hello() {
        String url = "http://api.heclouds.com/devices/691294037/datapoints"; //请求onenet平台691294037设备的甲醛浓度
        HttpMethod method = HttpMethod.GET;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        return httpClient.client(url, method, params);
    }
}
