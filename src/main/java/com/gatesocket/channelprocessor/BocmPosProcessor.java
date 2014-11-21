package com.gatesocket.channelprocessor;


import java.util.List;
import java.util.Map;

import com.capinfo.common.util.MoneyUtil;
import com.capinfo.payment.appcomm.ApplicationException;
import com.capinfo.payment.base.CDbUtil;
import com.capinfo.payment.payment.Payment4_0;
import com.gatesocket.Request;
import com.gatesocket.Response;
import com.gatesocket.Transaction;
import com.gatesocket.channel.ChannelException;
import com.gatesocket.channel.ChannelProcessor;
import com.gatesocket.channel.stream.StreamParser;
import com.gatesocket.channel.stream.Stream;


/**
 * 
 * @Creator Fx
 * @Create-Date 2012-10-09
 */
public class BocmPosProcessor  implements ChannelProcessor{

	private StreamParser streamParser;
	private static WriteLog log = WriteLog.instance();
	
	public void process(Transaction transaction) throws ChannelException {
		
		try {
			log.writeStringToFile("开始业务处理");
			
		    Request request= transaction.getRequest();
		    
		    byte[] content = (byte[]) request.getBody();
		    
		    log.writeStringToFile(content.toString());

		    BocReqHeader bocheader = this.getHeader(content);
		    
		    String TRAN_CODE = bocheader.getTRAN_CODE();
		    	        		    
		    Payment4_0 payment40 = new Payment4_0();
		    
		    if("000010".equals(TRAN_CODE)){  //查询订单信息交易
		    	log.writeStringToFile("订单查询交易处理开始！");
		    	String PAY_APP_NO = this.getMsg(content, 112, 32).trim();  //支付申请号
		    	log.writeStringToFile("PAY_APP_NO:["+PAY_APP_NO+"]");
//		    	DbUtil dbutil = new DbUtil();
		    	
		    	payment40.BankOID = PAY_APP_NO; //原始订单号
		    	payment40.PmodeID = 143;  //内卡支付  
					
		    	Response response = transaction.getResponse();
		    	byte[] responsebody = new byte[324]; //查询订单报文定长长度324
				for(int i=0;i<responsebody.length;i++){  
					responsebody[i]=' '; //初始化为空格  
				}
				
				this.buildresponse(responsebody, "000011".getBytes(), 0, 6);   //请求类型

				this.buildresponse(responsebody, rightfillblank(PAY_APP_NO.getBytes(), 20), 6, 20);  //公司业务流水号
				
				this.buildresponse(responsebody, rightfillblank(PAY_APP_NO.getBytes(), 32), 72, 32); //支付申请号
				
				CDbUtil dbUtil = null;
		    	try {
		    		log.writeStringToFile("根据订单号"+PAY_APP_NO+"查询数据库记录");
		    		
			        dbUtil = new CDbUtil(false);
			        payment40.setDBConn(dbUtil.getConnection());
			        payment40.LoadPayIDByBankMsg(null);
			        log.writeStringToFile("订单状态为"+payment40.PayStatus);
			        if(payment40.PayStatus!=2){  //该订单已支或支付中
			        	throw new ApplicationException(0, "300001", "0", "该订单已支付");  
			        }
					
					String XAmountCY = MoneyUtil.yuanToFen(payment40.XAmountCY.toString());   //订单金额
					log.writeStringToFile("订单金额为"+XAmountCY);
					String CardHolder = payment40.CardHolder;       //姓名
					String certificate = payment40.IDCardNo;     //证件号
					    
						this.buildresponse(responsebody, "000000".getBytes(), 26, 6);  //结果代码 	
						
						this.buildresponse(responsebody, rightfillblank("成功".getBytes(), 40), 32, 40); //结果返回信息
						
						this.buildresponse(responsebody,this.leftfillzero(XAmountCY.getBytes(), 12), 112, 12);
						
						if(null!=CardHolder&&CardHolder.trim().length()>0)
							this.buildresponse(responsebody,this.rightfillblank(CardHolder.getBytes(), 20), 124, 20);  //支付人姓名
							
						if(null!=certificate&&certificate.trim().length()>0)
						    this.buildresponse(responsebody,this.rightfillblank(certificate.getBytes(), 32), 144, 20);  //交款人证件号
					
					    this.buildresponse(responsebody,"00000000".getBytes(), 104, 8);  //校验码
					    
					    response.setBody(responsebody);										
					
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					this.buildresponse(responsebody, "300001".getBytes(), 26, 6);  //结果代码 	
					
					this.buildresponse(responsebody, "未找到你输入的订单，请确认订单号输入正确".getBytes(), 32, 40); //结果返回信息
					
					response.setBody(responsebody);
					log.writeStringToFile(e.getMessage());
				}finally {
		            dbUtil.closeConnection();
		            dbUtil = null;
		        }

		    }
		    if("000020".equals(TRAN_CODE)){  //缴费确认
		    	String PAY_APP_NO = this.getMsg(content, 112, 32).trim();  //支付申请号
		    	String AMOUNT = this.getMsg(content, 144, 12).trim();  //支付金额
		    	String PAYCOUNT = this.getMsg(content, 156, 2).trim();  //支付次数
		    	String TRAN_TYPE = this.getMsg(content, 158, 3).trim();  //CUP 银行卡
		    	String BATCH_NO = this.getMsg(content, 161, 6).trim();  //批次号
		    	String BILL_NO = this.getMsg(content, 167, 6).trim();  //票据号
		    	String Seq_NO = this.getMsg(content, 173, 6).trim();  //POS流水号
		    	String CARD_NO = this.getMsg(content, 179, 19).trim();  //卡号
		    	String SUB_AMOUNT = this.getMsg(content, 198, 12).trim();  //单笔的支付金额
		    	String TRAN_DATE = this.getMsg(content, 210, 6).trim();  //交易日期
		    	String TRAN_TIME = this.getMsg(content, 216, 6).trim();  //交易时间
		    	String SYSTRC_NO = this.getMsg(content, 222, 12).trim();  //系统参考号
		    	String AUTH_NO = this.getMsg(content, 234, 6).trim();  //授权码
		    	String Issuer_Id = this.getMsg(content, 240, 8).trim();  //预付费卡公司号
		    	String CARD_TYP = this.getMsg(content, 248, 3).trim();  //卡类别
		    	String CARD_NAME = this.getMsg(content, 251, 20).trim();  //卡类别名称
		    	
		    	payment40.BankOID = PAY_APP_NO; //原始订单号
		    	payment40.PmodeID = 143;  //内卡支付
		    	
		    	Response response = transaction.getResponse();
		    	byte[] responsebody = new byte[268]; //查询订单报文定长长度324
				for(int i=0;i<responsebody.length;i++){
					responsebody[i]=' '; //初始化为空格
				}
		    	
				log.writeStringToFile("缴费确认处理中");
				this.buildresponse(responsebody, "000021".getBytes(), 0, 6);   //请求类型

				this.buildresponse(responsebody, rightfillblank(PAY_APP_NO.getBytes(),20), 6, 20);  //公司业务流水号
				CDbUtil dbUtil = null;
				
		    	try {
		    		log.writeStringToFile("根据订单号"+PAY_APP_NO+"查询数据库记录");
			        dbUtil = new CDbUtil(false);
			        payment40.setDBConn(dbUtil.getConnection());
					payment40.LoadPayIDByBankMsg(null); //根据订单号查询PAYMENT40对象
					
					if (CARD_NO.trim().length()>0)
				    	payment40.CardNo = CARD_NO;
				    	
				    	String authStr = "";
				    	if (AUTH_NO.trim().length()>0) {
			                //授权码
			                authStr += "AC=" + AUTH_NO+ "|";
			            }
			            if (SYSTRC_NO.trim().length()>0) {
			                //系统参考号
			                authStr += "SN=" + SYSTRC_NO + "|";
			            }
				    	payment40.AuthCode = authStr;
				    	
				    	if(CARD_TYP.startsWith("5"))  {
				    		log.writeStringToFile("外卡交易");
				    	    payment40.updatePmodeId(payment40.OrderCDFK, 144);
				    	}

				    	else if(CARD_TYP.startsWith("0")){
				    		log.writeStringToFile("内卡交易");
				    	    payment40.updatePmodeId(payment40.OrderCDFK, 143);
				    	}

				    	payment40.PayStatus=1;  //更新状态为已支付
				    	payment40.update();
				    	
				    	this.buildresponse(responsebody, "000000".getBytes(), 26, 6);  //结果代码 	
						
						this.buildresponse(responsebody, rightfillblank("成功".getBytes(), 40), 32, 40); //结果返回信息
						
						if(null!=PAY_APP_NO&&PAY_APP_NO.trim().length()>0)
						this.buildresponse(responsebody,rightfillblank(PAY_APP_NO.getBytes(), 32), 72, 32); //支付申请号
						
						this.buildresponse(responsebody,"00000000".getBytes(), 104, 8);  //校验码
						
						if(null!=AMOUNT&&AMOUNT.trim().length()>0){
							this.buildresponse(responsebody,leftfillzero(AMOUNT.getBytes(), 12), 112, 12);  //订单总金额
							
							this.buildresponse(responsebody,leftfillzero(AMOUNT.getBytes(), 12), 124, 12);  //交易金额
						}
						
						
						this.buildresponse(responsebody,leftfillzero("0".getBytes(), 12), 136, 12);  //订单剩余支付金额
										    	
				  
						response.setBody(responsebody);
				    	
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					
                    this.buildresponse(responsebody, "300001".getBytes(), 26, 6);  //结果代码 	
					
					this.buildresponse(responsebody, "未找到你输入的订单，请确认订单号输入正确".getBytes(), 32, 40); //结果返回信息
					
					response.setBody(responsebody);
					log.writeStringToFile(e.getMessage());
				}
				finally {
		            dbUtil.closeConnection();
		            dbUtil = null;
		        }
		    	
		    }
		    		    		    
		} catch (ChannelException e) {
			throw new ChannelException(e);
		}
		catch (Exception e) {
			log.writeStringToFile("业务处理异常");
			log.writeStringToFile(e.getMessage());
			e.printStackTrace();
		}
		log.writeStringToFile("业务处理完成");

	}
	public static String getMsg(byte[] content,int start,int length){
		
        byte[] msg = new byte[length];
        for(int i = start;i<start+length;i++){
        	msg[i-start] = content[i];
        }
		return new String(msg);
		
	}
	
	public static BocReqHeader getHeader(byte[] content){
		BocReqHeader bocheader = new BocReqHeader();
		bocheader.setTRAN_CODE(BocmPosProcessor.getMsg(content, 0, 6));
		bocheader.setUSER(BocmPosProcessor.getMsg(content,6, 20));
		bocheader.setPASSWORD(BocmPosProcessor.getMsg(content,26, 10));
		bocheader.setBANK_CODE(BocmPosProcessor.getMsg(content,36, 8));
		bocheader.setINSURE_ID(BocmPosProcessor.getMsg(content,44, 8));
		bocheader.setMIDNO(BocmPosProcessor.getMsg(content,52, 15));
		bocheader.setTIDNO(BocmPosProcessor.getMsg(content,67, 8));
		bocheader.setBK_ACCT_DATE(BocmPosProcessor.getMsg(content,75, 8));
		bocheader.setBK_ACCT_TIME(BocmPosProcessor.getMsg(content,83, 6));
		bocheader.setBK_SERIAL(BocmPosProcessor.getMsg(content,89, 20));
		bocheader.setBK_TRAN_CHNL(BocmPosProcessor.getMsg(content,119, 3));
		log.writeStringToFile("TRAN_CODE:["+bocheader.getTRAN_CODE()+"]");
		log.writeStringToFile("USER:["+bocheader.getUSER()+"]");
		log.writeStringToFile("PASSWORD:["+bocheader.getPASSWORD()+"]");
		log.writeStringToFile("BANK_CODE:["+bocheader.getBANK_CODE()+"]");
		log.writeStringToFile("INSURE_ID:["+bocheader.getINSURE_ID()+"]");
		log.writeStringToFile("MIDNO:["+bocheader.getMIDNO()+"]");
		log.writeStringToFile("TIDNO:["+bocheader.getTIDNO()+"]");
		log.writeStringToFile("BK_ACCT_DATE:["+bocheader.getBK_ACCT_DATE()+"]");
		log.writeStringToFile("BK_ACCT_TIME:["+bocheader.getBK_ACCT_TIME()+"]");
		log.writeStringToFile("BK_SERIAL:["+bocheader.getBK_SERIAL()+"]");
		log.writeStringToFile("BK_TRAN_CHNL:["+bocheader.getBK_TRAN_CHNL()+"]");
		return bocheader;
	}
	
	public static byte[] buildresponse(byte[] responsebody,byte[] msg,int start,int length){
		for(int i=start;i<start+length;i++){
			responsebody[i] = msg[i-start];
		}
		return responsebody;
		
	}
	
	public static byte[] leftfillzero(byte[] msg,int length){
		byte[] newmsg = new byte[length];
		for(int i=0;i<length;i++){
			int dec = newmsg.length-msg.length;
			if(i<dec)
				newmsg[i] = '0';
			else
				newmsg[i] = msg[i-dec];
		}
		return newmsg;
	}
	
	public static byte[] rightfillblank(byte[] msg,int length){
		byte[] newmsg = new byte[length];
		for(int i=0;i<length;i++){
			if(i<msg.length)
				newmsg[i] = msg[i];
			else
				newmsg[i] = ' ';
		}
		return newmsg;
	}
	
	public static void main(String args[]){
		
//		String aa= "我买网111";
//		
//		byte[] bb = BocmPosProcessor.rightfillblank(aa.getBytes(), 15);
//		
//		byte[] responsebody = new byte[324];
//		for(int i=0;i<responsebody.length;i++){
//			responsebody[i]=' ';
//		}
//		
//		
//		BocmPosProcessor.buildresponse(responsebody, bb, 15, 15);
//		
//		BocmPosProcessor.buildresponse(responsebody, bb, 35, 15);
//		
//		System.out.println("["+new String(responsebody)+"]");
		


		
	}


}
