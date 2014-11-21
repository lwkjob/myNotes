package com.gatesocket.types;

public interface Type
{

	public static final int LEFT_PADDING = 1;
	public static final int RIGH_PADDING = 2;

	public abstract byte[] toBytes(String s);

	public abstract byte[] toBytes(String s, String s1);

	public abstract String toString(String s);

	public abstract String toString(String s, String s1);

	public abstract int getLength();
}
