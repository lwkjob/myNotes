package com.gatesocket.utils;

public class UUIDGen {
	private static long lastTime;
	private static long clockSeqAndNode;

	private UUIDGen()
	{
	}

	public static long getClockSeqAndNode()
	{
		return clockSeqAndNode;
	}

	public static synchronized long newTime()
	{
		long timeMillis = System.currentTimeMillis() * 10000L + 0x1b21dd213814000L;
		if (timeMillis > lastTime)
			lastTime = timeMillis;
		else
			timeMillis = ++lastTime;
		long time = timeMillis << 32;
		time |= (timeMillis & 0xffff00000000L) >> 16;
		time |= 4096L | timeMillis >> 48 & 4095L;
		return time;
	}

}
