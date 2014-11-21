package com.gatesocket.channelprocessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.capinfo.payment.base.CDbUtil;
import com.capinfo.payment.appcomm.ApplicationException;
/**
 * 
 * @Creator Fx
 * @Create-Date 2012-10-12
 */
public class DbUtil {
	
	 protected Map queryOrderByOrderId(String orderId) throws ApplicationException {
		 boolean useConnectionPool = false; 
		         Map ordMap = null;
		         CDbUtil dbUtil = null;
		         try {
		             dbUtil = new CDbUtil(useConnectionPool);
		             List paraList = new ArrayList();
		             String sql = "select exchange_money,card_owner,certificate from payment_trans where order_id=?";
		             paraList.add(orderId);
		             ordMap = dbUtil.queryOneRow(sql, paraList);
		             paraList = null;
		             sql = null;
		         } catch (ApplicationException e) {
		             throw e;
		         } finally {
		             dbUtil.closeConnection();
		             dbUtil = null;
		         }
		         return ordMap;
		     }

}
