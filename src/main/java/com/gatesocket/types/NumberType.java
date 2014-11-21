package com.gatesocket.types;

import com.gatesocket.utils.BytesUtil;
import java.io.UnsupportedEncodingException;


public class NumberType
	implements Type
{

	private int length;
	private int padflag;
	private byte tpl[];

	public NumberType(int length, int padflag, byte pad)
	{
		tpl = null;
		this.length = length;
		this.padflag = padflag;
		tpl = BytesUtil.init(pad, length);
	}

	public byte[] toBytes(String value)
	{
		return toBytes(value, "UTF-8");
	}

	public String toString(String value)
	{
		return toString(value, "UTF-8");
	}

	public String toString(String value, String charset)
	{
		return new String(toBytes(value));
	}

	public int getLength()
	{
		return length;
	}

	public byte[] toBytes(String value, String charset)
	{
		byte temp[];
		if (value == null || value.equals(""))
			return tpl;
		if (value.length() > length)
		{
			return value.substring(value.length() - length).getBytes();
		}
		temp = BytesUtil.read(tpl, length - value.length());
		if (padflag != 1)
			try {
				return BytesUtil.join(temp, value.getBytes(charset));
			} catch (UnsupportedEncodingException e) {
				
				e.printStackTrace();
			}
			else
		return BytesUtil.join(temp, value.getBytes());
//		return BytesUtil.join(value.getBytes(charset), temp);
		return BytesUtil.join(value.getBytes(), temp);
	}

}