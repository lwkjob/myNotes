package com.gatesocket.utils;

//Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
//Jad home page: http://kpdus.tripod.com/jad.html
//Decompiler options: packimports(3) fieldsfirst ansi space 
//Source File Name:   UUID.java

import java.io.*;
import org.omg.CORBA.portable.IDLEntity;

//Referenced classes of package com.eaio.uuid:
//			UUIDGen

public class UUID
	implements Comparable, Serializable, Cloneable, IDLEntity
{

	static final long serialVersionUID = 0x6731d8c298b9715bL;
	public long time;
	public long clockSeqAndNode;
	private static long lastTime;

	public UUID()
	{
		this(UUIDGen.newTime(), UUIDGen.getClockSeqAndNode());
	}

	public UUID(long time, long clockSeqAndNode)
	{
		this.time = time;
		this.clockSeqAndNode = clockSeqAndNode;
	}

	public UUID(UUID u)
	{
		this(u.time, u.clockSeqAndNode);
	}

	public int compareTo(Object o)
	{
		if (this == o)
			return 0;
		if (!(o instanceof UUID))
			throw new ClassCastException((new StringBuilder("The argument must be of type '")).append(getClass().getName()).append("'.").toString());
		UUID t = (UUID)o;
		if (time > t.time)
			return 1;
		if (time < t.time)
			return -1;
		if (clockSeqAndNode > t.clockSeqAndNode)
			return 1;
		return clockSeqAndNode >= t.clockSeqAndNode ? 0 : -1;
	}

	private void writeObject(ObjectOutputStream out)
		throws IOException
	{
		out.writeLong(time);
		out.writeLong(clockSeqAndNode);
	}

	private void readObject(ObjectInputStream in)
		throws IOException, ClassNotFoundException
	{
		time = in.readLong();
		clockSeqAndNode = in.readLong();
	}

	public final String toString()
	{
		return toStringBuffer(null).toString();
	}

	public StringBuffer toStringBuffer(StringBuffer in)
	{
		if (in == null)
			in = new StringBuffer(36);
		else
			in.ensureCapacity(in.length() + 36);
		in.append(Hex.asChars((int)(time >> 32))).append(Hex.asChars((short)(int)(time >> 16))).append(Hex.asChars((short)(int)time)).append(Hex.asChars((short)(int)(clockSeqAndNode >> 48))).append(Hex.asChars(clockSeqAndNode, 12));
		return in;
	}

	public int hashCode()
	{
		return (int)(time >> 32 ^ time ^ clockSeqAndNode >> 32 ^ clockSeqAndNode);
	}

	public Object clone()
	{
		return new UUID(this);
	}

	public final long getTime()
	{
		return time;
	}

	public final long getClockSeqAndNode()
	{
		return clockSeqAndNode;
	}

	public boolean equals(Object obj)
	{
		if (!(obj instanceof UUID))
			return false;
		return compareTo(obj) == 0;
	}

	public static UUID nilUUID()
	{
		return new UUID(0L, 0L);
	}
	
	
	public static char[] asChars(byte b[])
	{
		int len = b.length << 1;
		char out[] = new char[len--];
		for (int i = b.length - 1; i > -1; i--)
		{
			out[len--] = DIGITS[(byte)(b[i] & 0xf)];
			out[len--] = DIGITS[(byte)((b[i] & 0xf0) >> 4)];
		}

		return out;
	}
	private static final char DIGITS[] = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
		'a', 'b', 'c', 'd', 'e', 'f'
	};
}