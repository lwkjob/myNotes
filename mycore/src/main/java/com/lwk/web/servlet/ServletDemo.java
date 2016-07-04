package com.lwk.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by liwenke on 16/5/12.
 */
public class ServletDemo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            request.setAttribute("imfrom","ServletDemo Request");
//
//           request.getRequestDispatcher("/WEB-INF/jsp/fuck.jsp").forward(request,response);
//           System.out.println("换了");

//
//            response.sendRedirect("/jsp/fuck.jsp");
             response.setStatus(301);
             response.setHeader("Location","https://crm-dev.starlinked.com");
             return;
//            System.out.println("又换了1");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
