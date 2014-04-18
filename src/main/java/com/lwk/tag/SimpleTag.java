package com.lwk.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author lwkjob
 * 简单标签
 */
public class SimpleTag extends SimpleTagSupport {
	// 重写 doTag 方法，该方法在标签结束生成页面内容
	public void doTag() throws JspException, IOException {
		// 获取页面输出流，并输出字符串
		getJspContext().getOut().write("Hello World lwk");
	}
}