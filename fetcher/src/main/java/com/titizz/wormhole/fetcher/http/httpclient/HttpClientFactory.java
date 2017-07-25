package com.titizz.wormhole.fetcher.http.httpclient;


import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;

/**
 * Created by code4wt on 17/7/20.
 */
public class HttpClientFactory {

    private HttpClientFactory() {}

    private HttpClient buildHttpClient() {
        HttpRequestRetryHandler retryHander = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount > 3) {
                    // Do not retry if over max retry count
                    return false;
                }
                if (exception instanceof java.net.SocketTimeoutException) {
                    // 特殊处理
                    return true;
                }
                if (exception instanceof InterruptedIOException) {
                    // Timeout
                    return true;
                }
                if (exception instanceof UnknownHostException) {
                    // Unknown host
                    return false;
                }

                if (exception instanceof SSLException) {
                    // SSL handshake exception
                    return false;
                }
                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
                if (idempotent) {
                    // Retry if the request is considered idempotent
                    return true;
                }
                return false;
            }
        };

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(2000)
                .setSocketTimeout(5000).build();

        return HttpClientBuilder.create()
                .setConnectionManager(ConnectionManagerFactory.getConnectionManager())
                .setRetryHandler(retryHander)
                .setRedirectStrategy(new WormholeRedirectStrastegy())
                .build();
    }

    public static HttpClient getHttpClient() {
        return new HttpClientFactory().buildHttpClient();
    }
}
