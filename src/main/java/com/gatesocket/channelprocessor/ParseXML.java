package com.gatesocket.channelprocessor;

import java.io.File;
import java.util.*;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
/**
 * 
 * @Creator Fx
 * @Create-Date 2012-10-16
 */
public class ParseXML
{

	private static SAXBuilder builder = new SAXBuilder();
	private static Document doc = null;
	public static HashMap hashMap = new HashMap();

	public ParseXML()
	{
	}

	private static void init()
		throws Exception
	{
		if (doc == null)
			try
			{
				doc = builder.build(new File("../config/config.xml"));
			}
			catch (Exception e)
			{
				throw new Exception(e);
			}
	}

	public static HashMap getXmlConfig()
		throws Exception
	{
		try
		{
			init();
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
			throw new Exception(e1);
		}
		List ls = doc.getRootElement().getChildren("Parameter");
		String name;
		String value;
		for (Iterator i = ls.iterator(); i.hasNext(); hashMap.put(name, value))
		{
			Element e = (Element)i.next();
			name = e.getAttributeValue("name");
			value = e.getText();
		}

		return hashMap;
	}

	public static void main(String args1[]) throws Exception
	{
		
		HashMap hashMap = ParseXML.getXmlConfig();
		
		String a = hashMap.get("serverport").toString();
//		int port = Integer.parseInt((String) hashMap.get("serverport"));
//		int poolsize = Integer.parseInt((String) hashMap.get("poolsize"));
		
		System.out.println(a);
	}
	

}