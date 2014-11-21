package com.gatesocket.channelprocessor;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-10-17
 */
public class WriteLog
{

	private static String fileName = "";
	private String threadName;
	private static WriteLog writeLog = null;

	public WriteLog()
		throws Exception
	{
		threadName = "";
		HashMap hashMap = ParseXML.hashMap;
		if (hashMap == null)
			hashMap = ParseXML.getXmlConfig();
		else
		if (hashMap != null && hashMap.size() == 0)
			hashMap = ParseXML.getXmlConfig();
		fileName = (String)hashMap.get("logFileDir") + (String)hashMap.get("logFileName");
	}

	public static WriteLog instance()
	{
		if (writeLog == null)
			try
			{
				writeLog = new WriteLog();
			}
			catch (Exception e)
			{
				System.out.println("»’÷æ“Ï≥£");
				e.printStackTrace();
			}
		return writeLog;
	}

	public String getLocalTime()
	{
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String currentDate = simpleFormat.format(date);
		return currentDate;
	}

	public boolean writeStringToFile(String outPutString)
	{
		try
		{
			DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName, true)));
			outPutString = new String(outPutString.getBytes("GB2312"), "ISO8859_1");
			threadName = Thread.currentThread().getName();
			String currentDate = getLocalTime();
			out.writeBytes(currentDate + " [" + threadName + "] " + outPutString + "\n");
			out.close();
		}
		catch (Exception e)
		{
			System.out.println("fileName = [" + fileName + "]" + e.toString());
			return false;
		}
		return true;
	}

	public boolean writeStringToFile(char c)
	{
		try
		{
			DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName, true)));
			out.writeBytes((new StringBuffer(String.valueOf(c))).toString());
			out.close();
		}
		catch (Exception e)
		{
			System.out.println("fileName = [" + fileName + "]" + e.toString());
			return false;
		}
		return true;
	}

	public void printBuffer(byte buff[], int start, int end)
	{
		byte tmpBuffer[] = new byte[end - start];
		System.arraycopy(buff, start, tmpBuffer, 0, end - start);
		writeStringToFile(new String(tmpBuffer));
	}


}
