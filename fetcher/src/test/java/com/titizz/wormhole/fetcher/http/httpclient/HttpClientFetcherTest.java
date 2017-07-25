package com.titizz.wormhole.fetcher.http.httpclient;

import com.titizz.wormhole.fetcher.FetchItem;
import com.titizz.wormhole.fetcher.FetchResult;
import com.titizz.wormhole.fetcher.HttpConstant;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by code4wt on 17/7/25.
 */
public class HttpClientFetcherTest {

    @Test
    public void fetch() throws Exception {
        FetchItem fetchItem = new FetchItem();
        fetchItem.setUrl("http://www.qq.com/");
        fetchItem.setMethod("GET");

        HttpClientFetcher fetcher = new HttpClientFetcher();
        FetchResult fetchResult = fetcher.fetch(fetchItem);
        assertTrue(fetchResult.getStatusCode().equals(HttpConstant.StatusCode.CODE_200));
    }
}