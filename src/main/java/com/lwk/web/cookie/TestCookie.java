package com.lwk.web.cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class TestCookie
 */
public class TestCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TestCookie() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie nameCookie=new Cookie("myname", "lwk");
		nameCookie.setMaxAge(4);
		response.addCookie(nameCookie);
		request.getRequestDispatcher("/web/cookieTest.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
