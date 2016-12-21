package com.lwk.httpclient;

import java.util.Map;


public class HttpResponse {
    String html;
    int status;

    public HttpResponse() {
        this(-1, null);
    }

    public HttpResponse(int status, String html, Map<String, String> headers) {
        this.html = html;
        this.status = status;
    }

    public HttpResponse(int status, String html) {
        this(status, html, null);
    }

    public String getHtml() {
        return html;
    }

    public int getStatus() {
        return status;
    }
}
