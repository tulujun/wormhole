package com.titizz.wormhole.fetcher.http.httpclient;

import com.titizz.wormhole.fetcher.FetchItem;
import com.titizz.wormhole.fetcher.FetchResult;
import com.titizz.wormhole.fetcher.Fetcher;
import com.titizz.wormhole.fetcher.HttpConstant;
import com.titizz.wormhole.fetcher.utils.HttpClientUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Created by code4wt on 17/7/20.
 */
public class HttpClientFetcher implements Fetcher {

    @Override
    public FetchResult fetch(FetchItem fetchItem) {
        HttpClient httpClient = HttpClientFactory.getHttpClient();
        FetchResult fetchResult = null;
        try {
            HttpUriRequest request = createRequest(fetchItem);
            HttpResponse httpResponse = httpClient.execute(request);
            fetchResult = handleHttpResponse(fetchItem, request, httpResponse);
        } catch (IOException e) {

        }

        return fetchResult;
    }

    private FetchResult handleHttpResponse(FetchItem fetchItem,
            HttpUriRequest httpUriRequest,
            HttpResponse httpResponse) throws IOException {

        HttpEntity entity = httpResponse.getEntity();
        long contentLength = entity.getContentLength();

        FetchResult fetchResult = new FetchResult();
        fetchResult.setFetchItem(fetchItem);
        fetchResult.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        fetchResult.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        fetchResult.setHeaders(HttpClientUtils.convertHeaders(httpResponse.getAllHeaders()));

        if (contentLength > 5 * 1024 * 1024) {
            httpUriRequest.abort();
            fetchResult.setStatus(HttpConstant.Status.STATUS_FETCH_CONTENT_LENGTH_EXCEEDINNG);
            return fetchResult;
        }

        fetchResult.setReponseBody(EntityUtils.toByteArray(entity));

        return fetchResult;
    }

    private HttpUriRequest createRequest(FetchItem fetchItem) {

        RequestBuilder requestBuilder = selectRequestMethod(fetchItem).setUri(fetchItem.getUrl());
        if (fetchItem.getHeaders() != null) {
            for (Map.Entry<String, String> headerEntry : fetchItem.getHeaders().entrySet()) {
                requestBuilder.addHeader(headerEntry.getKey(), headerEntry.getValue());
            }
        }

        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
        requestBuilder.setConfig(requestConfigBuilder.build());

        return requestBuilder.build();
    }

    private RequestBuilder selectRequestMethod(FetchItem fetchItem) {
        String method = fetchItem.getMethod();
        if (method == null || method.equalsIgnoreCase(HttpConstant.Method.GET)) {
            return RequestBuilder.get();
        } else if (method.equalsIgnoreCase(HttpConstant.Method.POST)) {
            RequestBuilder builder = RequestBuilder.post();
            HttpRequestBody httpRequestBody = fetchItem.getRequestBody();
            if (httpRequestBody != null) {
                ByteArrayEntity entity = new ByteArrayEntity(httpRequestBody.getBody());
                entity.setContentType(httpRequestBody.getContentType());
                builder.setEntity(entity);
            }

            return builder;
        }

        throw new IllegalArgumentException("Unsupport HTTP Method " + method);
    }
}
