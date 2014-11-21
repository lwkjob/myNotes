package com.gatesocket.utils;

public final class Hex
{

	private static final char DIGITS[] = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
		'a', 'b', 'c', 'd', 'e', 'f'
	};

	private Hex()
	{
	}

	public static char[] asChars(byte in)
	{
		return asChars(in, 2);
	}

	public static char[] asChars(byte in, int length)
	{
		char out[] = new char[length--];
		for (int i = length; i > -1; i--)
		{
			out[i] = DIGITS[(byte)(in & 0xf)];
			in >>= 4;
		}

		return out;
	}

	public static char[] asChars(int in)
	{
		return asChars(in, 8);
	}

	public static char[] asChars(int in, int length)
	{
		char out[] = new char[length--];
		for (int i = length; i > -1; i--)
		{
			out[i] = DIGITS[(byte)(in & 0xf)];
			in >>= 4;
		}

		return out;
	}

	public static char[] asChars(long in)
	{
		return asChars(in, 16);
	}

	public static char[] asChars(long in, int length)
	{
		char out[] = new char[length--];
		for (int i = length; i > -1; i--)
		{
			out[i] = DIGITS[(byte)(int)(in & 15L)];
			in >>= 4;
		}

		return out;
	}

	public static char[] asChars(short in)
	{
		return asChars(in, 4);
	}

	public static char[] asChars(short in, int length)
	{
		char out[] = new char[length--];
		for (int i = length; i > -1; i--)
		{
			out[i] = DIGITS[(byte)(in & 0xf)];
			in >>= 4;
		}

		return out;
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

	public static long parseLong(String s)
		throws NullPointerException
	{
		s = s.toLowerCase();
		long out = 0L;
		byte shifts = 0;
		for (int i = 0; i < s.length() && shifts < 16; i++)
		{
			char c = s.charAt(i);
			if (c > '/' && c < ':')
			{
				out <<= 4;
				shifts++;
				out |= c - 48;
			} else
			if (c > '`' && c < 'g')
			{
				shifts++;
				out <<= 4;
				out |= c - 87;
			}
		}

		return out;
	}

	public static int parseInt(String s)
		throws NullPointerException
	{
		s = s.toLowerCase();
		int out = 0;
		byte shifts = 0;
		for (int i = 0; i < s.length() && shifts < 8; i++)
		{
			char c = s.charAt(i);
			if (c > '/' && c < ':')
			{
				out <<= 4;
				shifts++;
				out |= c - 48;
			} else
			if (c > '`' && c < 'g')
			{
				shifts++;
				out <<= 4;
				out |= c - 87;
			}
		}

		return out;
	}

	public static short parseShort(String s)
		throws NullPointerException
	{
		s = s.toLowerCase();
		short out = 0;
		byte shifts = 0;
		for (int i = 0; i < s.length() && shifts < 4; i++)
		{
			char c = s.charAt(i);
			if (c > '/' && c < ':')
			{
				out <<= 4;
				shifts++;
				out |= c - 48;
			} else
			if (c > '`' && c < 'g')
			{
				shifts++;
				out <<= 4;
				out |= c - 87;
			}
		}

		return out;
	}

	public static byte parseByte(String s)
		throws NullPointerException
	{
		s = s.toLowerCase();
		byte out = 0;
		byte shifts = 0;
		for (int i = 0; i < s.length() && shifts < 2; i++)
		{
			char c = s.charAt(i);
			if (c > '/' && c < ':')
			{
				out <<= 4;
				shifts++;
				out |= c - 48;
			} else
			if (c > '`' && c < 'g')
			{
				shifts++;
				out <<= 4;
				out |= c - 87;
			}
		}

		return out;
	}

}