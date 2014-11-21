package com.gatesocket.channel.stream;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.gatesocket.types.NumberType;
import com.gatesocket.types.Type;
import com.gatesocket.channel.stream.Header;
import com.gatesocket.channel.stream.RequestHeader;
import com.gatesocket.channel.stream.ResponseHeader;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-30
 */
public class SimpleHeader implements RequestHeader, ResponseHeader {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2481846463899002487L;

	public static final String MSG_LEN = "MEG_LEN";

	protected Type lenType = null;

	protected String value = "";

	public SimpleHeader(int length) {
		this.lenType = new NumberType(length, Type.LEFT_PADDING, "0".getBytes()[0]);
		setBodyLength(0);
	}

	public Header convert() {
		return this;
	}

	public void fromMap(Map<String, String> map) {
		this.value = map.get(MSG_LEN);
		if(value==null){
			value="0";
		}
		this.setBodyLength(Integer.parseInt(value));
		this.value = this.getBodyLength() + "";
	}

	public int getBodyLength() {
		String val = this.lenType.toString(this.value);
		return Integer.parseInt(val);
	}

	public byte[] getHeader(int index) {
		return new byte[] {};
	}

	public byte[] getHeader(String index) {
		return new byte[] {};
	}

	public byte[] getHeaderContent() {
		return this.lenType.toBytes(value);
	}

	public int getLength() {
		return this.lenType.getLength();
	}

	public Iterator<String> keys() {
		return Arrays.asList(MSG_LEN).iterator();
	}

	public void parse(byte[] content) {
		this.value = new String(content);
	}

	public void setBodyLength(int length) {
		this.value = length + "";
		this.value = this.lenType.toString(this.value);
	}

	/**
	 * invalid method for simple header
	 * 
	 * @param index
	 * @param value
	 */
	public void setHeader(int index, byte[] value) {

	}

	/**
	 * invalid method for simple header
	 * 
	 * @param index
	 * @param value
	 */
	public void setHeader(String index, byte[] value) {

	}

	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(MSG_LEN, this.getBodyLength() + "");
		return map;
	}

	@Override
	public Header clone() throws CloneNotSupportedException {
		SimpleHeader header = new SimpleHeader(this.lenType.getLength());
		header.setBodyLength(this.getBodyLength());
		return header;
	}

}
