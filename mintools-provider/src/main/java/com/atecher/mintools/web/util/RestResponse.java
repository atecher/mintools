package com.atecher.mintools.web.util;

import java.util.List;

/**
 * @description:
 * @author: atecher
 * @date: 2018/10/16 下午4:23
 */
public class RestResponse {
    private List<RestHeaderParam> headers;
    private String body;

    public List<RestHeaderParam> getHeaders() {
        return headers;
    }

    public void setHeaders(List<RestHeaderParam> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
