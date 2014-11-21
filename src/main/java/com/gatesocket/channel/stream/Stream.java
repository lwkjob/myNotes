package com.gatesocket.channel.stream;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-8
 */
public class Stream implements Cloneable {

	private Header header;

	private byte[] msg;

	public Stream(Header header, byte[] msg) {
		this.header = header;
		this.msg = msg;
	}

	public Header getHeader() {
		return this.header;
	}

	public byte[] getMessage() {
		return this.msg;
	}

	public Stream clone() throws CloneNotSupportedException {
		return new Stream(header.clone(), msg);
	}
	
	public String toString(){
		return new String(join(header.getHeaderContent(), msg));
	}
	
	public byte[] toBytes(){
		return join(header.getHeaderContent(), msg);
	}
	
	public static byte[] join(byte left[], byte right[])
	{
		byte temp[] = new byte[left.length + right.length];
		System.arraycopy(left, 0, temp, 0, left.length);
		System.arraycopy(right, 0, temp, left.length, right.length);
		return temp;
	}
}
