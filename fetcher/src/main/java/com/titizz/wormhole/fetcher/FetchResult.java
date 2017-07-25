package com.titizz.wormhole.fetcher;

import java.util.List;
import java.util.Map;

/**
 * Created by code4wt on 17/7/24.
 */
public class FetchResult {

    private FetchItem fetchItem;

    private Integer statusCode;

    private Byte status;

    private byte[] reponseBody;

    private Map<String, List<String>> headers;


    public FetchItem getFetchItem() {
        return fetchItem;
    }

    public void setFetchItem(FetchItem fetchItem) {
        this.fetchItem = fetchItem;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public byte[] getReponseBody() {
        return reponseBody;
    }

    public void setReponseBody(byte[] reponseBody) {
        this.reponseBody = reponseBody;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }
}
