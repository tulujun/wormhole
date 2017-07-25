package com.titizz.wormhole.fetcher.http.httpclient;

import com.google.gson.Gson;

/**
 * Created by code4wt on 17/7/23.
 */
public class HttpRequestBody {

    private byte[] body;

    private String contentType;

    private String encoding;

    public static HttpRequestBody create(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, HttpRequestBody.class);
    }

    public byte[] getBody() {
        return body;
    }

    public String getContentType() {
        return contentType;
    }

    public String getEncoding() {
        return encoding;
    }
}
