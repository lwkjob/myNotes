package com.lwk.filter.request;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;


public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private  Logger LOG = LoggerFactory.getLogger(BodyReaderHttpServletRequestWrapper.class);
    private final byte[] body;

    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request)
            throws IOException {
        super(request);
        LOG.info(" 请求地址  \n " + request.getRequestURL());
        body = IOUtils.toByteArray(request.getInputStream());
        LOG.info(" 请求参数  \n "+IOUtils.toString(body,"UTF-8"));

        LOG.info("请求头信息:---------------------");
        StringBuffer sbf=new StringBuffer("\n");
        Enumeration enumeration2 =request.getHeaderNames();
        while (enumeration2.hasMoreElements()){
            String key=(String)enumeration2.nextElement();
            sbf.append(key + " = " + request.getHeader(key)+"\n");
        }
        LOG.info(sbf.toString());
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

}  