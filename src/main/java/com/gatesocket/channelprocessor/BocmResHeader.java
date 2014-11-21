package com.gatesocket.channelprocessor;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-10-09
 */
public class BocmResHeader {

	private String TRAN_CODE; //请求类型
	private String RCPT_NO;  //公司业务流水号
	private String RSLT_CODE; //结果代码
	private String RSLT_MSG;  //结果返回信息
	
	public String getTRAN_CODE() {
		return TRAN_CODE;
	}
	public void setTRAN_CODE(String tran_code) {
		TRAN_CODE = tran_code;
	}
	public String getRCPT_NO() {
		return RCPT_NO;
	}
	public void setRCPT_NO(String rcpt_no) {
		RCPT_NO = rcpt_no;
	}
	public String getRSLT_CODE() {
		return RSLT_CODE;
	}
	public void setRSLT_CODE(String rslt_code) {
		RSLT_CODE = rslt_code;
	}
	public String getRSLT_MSG() {
		return RSLT_MSG;
	}
	public void setRSLT_MSG(String rslt_msg) {
		RSLT_MSG = rslt_msg;
	}
}
