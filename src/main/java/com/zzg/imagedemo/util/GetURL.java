package com.zzg.imagedemo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class GetURL {

    public static String getMuXiAoGuo(String uri) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json;charset=UTF-8"));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        String body = response.getBody();
        HashMap<String, JSON> params = JSONObject.parseObject(body, HashMap.class);
        System.out.println(params);
        JSONArray data = (JSONArray) params.get("data");
        JSONObject imgUrl = (JSONObject) data.get(0);
        String url = imgUrl.get("imgurl").toString();


        return  url;
    }
}
