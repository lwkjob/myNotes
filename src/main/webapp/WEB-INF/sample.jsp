<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@page import="com.octo.captcha.service.CaptchaServiceException"%>
<%@page import="yanzm.CaptchaServiceSingleton"%>
<%
	Boolean isResponseCorrect = Boolean.FALSE;
	//remenber that we need an id to validate!
	String captchaId = request.getSession().getId();
	//retrieve the response
	String responsestr = request.getParameter("j_captcha_response");
	// Call the Service method
	try {
		isResponseCorrect = CaptchaServiceSingleton.getInstance().validateResponseForID(captchaId, responsestr);
		if(isResponseCorrect){
			
		}else{
			out.print("It's worng......");
		}
	} catch (CaptchaServiceException e) {
		//should not happen, may be thrown if the id is not valid
	}
%>