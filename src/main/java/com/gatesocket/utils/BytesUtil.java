package com.gatesocket.utils;

public abstract class BytesUtil
{

	public static final char digits[] = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
		'a', 'b', 'c', 'd', 'e'
	};

	public BytesUtil()
	{
	}

	public static int toInteger(byte bytes[])
	{
		return Integer.parseInt(new String(bytes));
	}

	public static byte[] join(byte left[], byte right[])
	{
		byte temp[] = new byte[left.length + right.length];
		System.arraycopy(left, 0, temp, 0, left.length);
		System.arraycopy(right, 0, temp, left.length, right.length);
		return temp;
	}

	public static byte[] remove(byte bytes[], int length)
	{
		byte temp[] = new byte[bytes.length - length];
		System.arraycopy(bytes, length, temp, 0, bytes.length - length);
		return temp;
	}

	public static byte[] read(byte bytes[], int length)
	{
		byte temp[] = new byte[length];
		System.arraycopy(bytes, 0, temp, 0, length);
		return temp;
	}

	public static String printHex(byte buffer[], int offset, int len)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++)
		{
			byte b = buffer[i + offset];
			if (sb.length() > 0)
				sb.append(' ');
			sb.append(digits[b >> 4 & 0xf]);
			sb.append(digits[b & 0xf]);
		}

		return sb.toString();
	}

	public static boolean equals(byte a1[], byte a2[])
	{
		if (a1.length != a2.length)
			return false;
		else
			return equals(a1, 0, a2, 0, a1.length);
	}

	public static boolean equals(byte a1[], int a1Offset, byte a2[], int a2Offset, int length)
	{
		if (a1.length < a1Offset + length || a2.length < a2Offset + length)
			return false;
		while (length-- > 0) 
			if (a1[a1Offset++] != a2[a2Offset++])
				return false;
		return true;
	}

	public static int index(byte message[], byte offset[])
	{
		for (int i = 0; i < message.length; i++)
			if (message[i] == offset[0])
			{
				for (int j = 1; j < offset.length; j++)
				{
					if (message.length <= i + j)
						return -1;
					if (offset[j] != message[i + j])
						return -1;
				}

				return i;
			}

		return -1;
	}

	public static String printHex(byte buffer[])
	{
		return printHex(buffer, 0, buffer.length);
	}

	public static byte[] init(byte value, int length)
	{
		byte bytes[] = new byte[length];
		for (int i = 0; i < length; i++)
			bytes[i] = value;

		return bytes;
	}

}
