package com.lwk.web.intercepter;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liwenke on 16/5/12.
 */
public class HttpSchemIntercepter extends HandlerInterceptorAdapter{
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("什么意思");
        if(handler instanceof HandlerMethod) {
            if (!"https".equals(request.getScheme())){
                response.setStatus(301);
                response.setHeader("Location","https://crm-dev.starlinked.com");
                return false;
            }
        }

        return true;
    }

}
