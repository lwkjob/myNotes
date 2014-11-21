package com.gatesocket.channelprocessor;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-10-09
 */
public class BocReqHeader {

	private String TRAN_CODE;  //交易代码
	private String USER;       //收款员用户名
	private String PASSWORD;   //收款员密码
	private String BANK_CODE;  //收单行号
	private String INSURE_ID;  //公司编码
	private String MIDNO;      //商户号
	private String TIDNO;      //终端号
	private String BK_ACCT_DATE;   //银行交易日期格式YYYYMMDD
	private String BK_ACCT_TIME;   //银行交易时间格式HHMMSS
	private String BK_SERIAL;      //银行交易流水
	private String BK_TRAN_CHNL;   //银行交易渠道
	
	
	public String getTRAN_CODE() {
		return TRAN_CODE;
	}
	public void setTRAN_CODE(String tran_code) {
		TRAN_CODE = tran_code;
	}
	public String getUSER() {
		return USER;
	}
	public void setUSER(String user) {
		USER = user;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String password) {
		PASSWORD = password;
	}
	public String getBANK_CODE() {
		return BANK_CODE;
	}
	public void setBANK_CODE(String bank_code) {
		BANK_CODE = bank_code;
	}
	public String getINSURE_ID() {
		return INSURE_ID;
	}
	public void setINSURE_ID(String insure_id) {
		INSURE_ID = insure_id;
	}
	public String getMIDNO() {
		return MIDNO;
	}
	public void setMIDNO(String midno) {
		MIDNO = midno;
	}
	public String getTIDNO() {
		return TIDNO;
	}
	public void setTIDNO(String tidno) {
		TIDNO = tidno;
	}
	public String getBK_ACCT_DATE() {
		return BK_ACCT_DATE;
	}
	public void setBK_ACCT_DATE(String bk_acct_date) {
		BK_ACCT_DATE = bk_acct_date;
	}
	public String getBK_ACCT_TIME() {
		return BK_ACCT_TIME;
	}
	public void setBK_ACCT_TIME(String bk_acct_time) {
		BK_ACCT_TIME = bk_acct_time;
	}
	public String getBK_SERIAL() {
		return BK_SERIAL;
	}
	public void setBK_SERIAL(String bk_serial) {
		BK_SERIAL = bk_serial;
	}
	public String getBK_TRAN_CHNL() {
		return BK_TRAN_CHNL;
	}
	public void setBK_TRAN_CHNL(String bk_tran_chnl) {
		BK_TRAN_CHNL = bk_tran_chnl;
	}
	
	public static String setmsg(int c){
		String a = "";
		for (int i=0;i<c;i++)
		{
			a =a+" ";
		}
		return a;
	}
	
	public static void main(String args[]){
		BocReqHeader bocheader = new BocReqHeader();
		bocheader.setTRAN_CODE("100011");
		String msg = "[100011"+bocheader.setmsg(30)+"BOCOMPOS"+"12345678"+"payeasenet12345"+"pos88888"+"20121017"+"104423"+"4008527527"+bocheader.setmsg(10)+"POS"+"95731631"+bocheader.setmsg(23)+"]";
		System.out.println(msg);
	}
	
	
}
