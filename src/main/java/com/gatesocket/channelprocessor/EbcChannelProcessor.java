package com.gatesocket.channelprocessor;

import java.util.Map;

import org.apache.log4j.Logger;

import com.capinfo.common.util.MoneyUtil;
import com.capinfo.payment.appcomm.ApplicationException;
import com.capinfo.payment.base.CDbUtil;
import com.capinfo.payment.ebcross.EbcResponseProcess;
import com.capinfo.payment.ebcross.XmlPacket;
import com.capinfo.payment.payment.Payment4_0;
import com.gatesocket.Request;
import com.gatesocket.Response;
import com.gatesocket.Transaction;
import com.gatesocket.channel.ChannelException;
import com.gatesocket.channel.ChannelProcessor;
import com.gatesocket.channel.stream.StreamParser;

public class EbcChannelProcessor implements ChannelProcessor{
	private StreamParser streamParser;
	protected Logger log = Logger.getRootLogger();
	
	public void process(Transaction transaction) throws ChannelException {
		try{
			log.info("开始业务处理");		
		    Request request= transaction.getRequest();		    
		    byte[] content = (byte[]) request.getBody();		    
		    String resdata = content.toString();
		    log.info(resdata);		    
		    EbcResponseProcess ebcresponse = new EbcResponseProcess();
		    ebcresponse.process(resdata);						
		    log.info("业务处理完成");	
		}
		catch(Exception e){
			log.error(e.getMessage());	
		}	 			
	}
	public static String getMsg(byte[] content,int start,int length){
		
        byte[] msg = new byte[length];
        for(int i = start;i<start+length;i++){
        	msg[i-start] = content[i];
        }
		return new String(msg);
		
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
