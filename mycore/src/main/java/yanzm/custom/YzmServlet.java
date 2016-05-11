package yanzm.custom;


import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Yzm
 */
public class YzmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public YzmServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("image/jpg");
		 Captcha cap=Captcha.getInstance();
		 cap.set(100, 30);
		 String checkcode=cap.generateCheckcode();
		 request.getSession().setAttribute("checkcode", checkcode);
		 OutputStream out=response.getOutputStream();
		 ImageIO.write(cap.generateCheckImg(checkcode), "jpg", out);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
