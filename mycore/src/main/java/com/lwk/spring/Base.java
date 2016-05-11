package com.lwk.spring;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liwenke on 16/3/16.
 */
public class Base {
    public void getHttpServlet(){

        //
        HttpServletRequest request=  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

    }
}
