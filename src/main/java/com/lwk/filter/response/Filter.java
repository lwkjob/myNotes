package com.lwk.filter.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by R9L4H0W on 2016/2/26.
 */
public class Filter implements javax.servlet.Filter {
    private  static Logger logger= LoggerFactory.getLogger(Filter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest servletRequest = (HttpServletRequest) req;
        HttpServletResponse servletResponse = (HttpServletResponse) resp;

        final CopyPrintWriter writer = new CopyPrintWriter(servletResponse.getWriter());

//        chain.doFilter(servletRequest, new HttpServletResponseWrapper(servletResponse) {
//            @Override
//            public PrintWriter getWriter() {
//                return writer;
//            }
//        });
//
//
//        logger.info(writer.getCopy());

        chain.doFilter(req,resp);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
