package com.gatesocket.channel.stream;

import java.util.Iterator;
import java.util.Map;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-8
 */
public abstract interface Header extends java.io.Serializable, Cloneable {

	/**
	 * 
	 * @return
	 */
	Iterator<String> keys();

	/**
	 * 
	 * @param index
	 * @return
	 */
	byte[] getHeader(int index);

	/**
	 * 
	 * @param index
	 * @return
	 */
	byte[] getHeader(String index);

	/**
	 * 
	 * @param index
	 * @param value
	 */
	void setHeader(int index, byte[] value);

	/**
	 * 
	 * @param index
	 * @param value
	 */
	void setHeader(String index, byte[] value);

	/**
	 * 
	 * @return
	 */
	byte[] getHeaderContent();

	/**
	 * 
	 * @return
	 */
	int getLength();

	/**
	 * 
	 * @return
	 */
	int getBodyLength();

	/**
	 * 
	 */
	void setBodyLength(int length);

	/**
	 * 交换关键域，�?般是发起方和受理方地�?进行交换
	 * 
	 * @return
	 */
	Header convert();

	/**
	 * 
	 * @param content
	 */
	void parse(byte[] content);

	/**
	 * 
	 * @return
	 */
	Map<String, String> toMap();

	/**
	 * 
	 * @param map
	 */
	void fromMap(Map<String, String> map);

	/**
	 * 
	 * @return
	 * @throws CloneNotSupportedException
	 */
	Header clone() throws CloneNotSupportedException;
}
