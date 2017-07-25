package com.titizz.wormhole.fetcher.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.http.Header;

import java.util.List;
import java.util.Map;

/**
 * Created by code4wt on 17/7/25.
 */
public class HttpClientUtils {

    public static Map<String, List<String>> convertHeaders(Header[] headers) {
        Map<String, List<String>> headerMap = Maps.newHashMap();
        for (Header header : headers) {
            String key = header.getName();
            String value = header.getValue();

            if (!headerMap.containsKey(key)) {
                headerMap.put(key, Lists.newArrayList(value));
                continue;
            }

            headerMap.get(key).add(value);
        }

        return headerMap;
    }
}
