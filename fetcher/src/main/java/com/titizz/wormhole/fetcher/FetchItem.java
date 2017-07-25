package com.titizz.wormhole.fetcher;

import com.titizz.wormhole.fetcher.http.httpclient.HttpRequestBody;

import java.util.Map;

/**
 * Created by code4wt on 17/7/20.
 */
public class FetchItem {

    private String url;

    private String method;

    private Map<String, String> headers;

    private Map<String, String> cookies;

    private HttpRequestBody requestBody;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public HttpRequestBody getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(HttpRequestBody requestBody) {
        this.requestBody = requestBody;
    }
}
