package com.lwk.web.cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetCookie
 */
public class GetCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCookie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest response, HttpServletResponse request)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies=request.getCookies();
		if(cookies.length==2){
			request.setAttribute("myName", cookies[1].getValue());
			request.setAttribute("cookieTime", cookies[1].getMaxAge());
		}else{
			request.setAttribute("myName", "cookies失效了");
		}
		
		for (int i = 0; i < cookies.length; i++) {
			System.out.println(cookies[i].getName()+"="+cookies[i].getValue()+"="+cookies[i].getVersion());
		}
		request.getRequestDispatcher("/web/cookieTest.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest response, HttpServletResponse request)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
	}

}
