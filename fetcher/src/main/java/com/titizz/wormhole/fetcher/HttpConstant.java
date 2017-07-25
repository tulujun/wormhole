package com.titizz.wormhole.fetcher;

/**
 * Created by code4wt on 17/7/23.
 */
public class HttpConstant {

    public static abstract class Method {

        public static final String GET = "GET";

        public static final String HEAD = "HEAD";

        public static final String POST = "POST";

        public static final String PUT = "PUT";

        public static final String DELETE = "DELETE";

        public static final String TRACE = "TRACE";

        public static final String CONNECT = "CONNECT";
    }

    public static abstract class StatusCode {

        public static final Integer CODE_200 = 200;

        public static final Integer CODE_400 = 400;

        public static final Integer CODE_401 = 401;

        public static final Integer CODE_403 = 403;

        public static final Integer CODE_404 = 404;

        public static final Integer CODE_500 = 500;

        public static final Integer CODE_502 = 502;

        public static final Integer CODE_503 = 503;

        public static final Integer CODE_504 = 504;
    }

    public static abstract class Status {

        public static final Byte STATUS_DB_UNFETCHED = 0x01;

        public static final Byte STATUS_DB_FETCHED = 0x02;

        public static final Byte STATUS_DB_GONE = 0x03;

        public static final Byte STATUS_DB_CONTENT_LENGTH_EXCEEDINNG = 0x05;

        public static final Byte STATUS_DB_NOTMODIFIED = 0x06;

        public static final Byte STATUS_DB_MAX = 0x1f;


        public static final Byte STATUS_FETCH_SUCCESS = 0x21;

        public static final Byte STATUS_FETCH_RETRY = 0x22;

        public static final Byte STATUS_FETCH_GONE = 0x23;

        public static final Byte STATUS_FETCH_CONTENT_LENGTH_EXCEEDINNG = 0x24;

        public static final Byte STATUS_FETCH_NOTMODIFIED = 0x25;

        public static final Byte STATUS_FETCH_MAX = 0x3f;

    }
}
