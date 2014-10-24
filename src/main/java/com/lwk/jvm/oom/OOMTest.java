package com.lwk.jvm.oom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=c:/memoryLeakDemo.hprof
-XX:+PrintGCDetails 
-XX:+PrintGCDateStamps 
-XX:+PrintGCApplicationConcurrentTime
-XX:+PrintGCApplicationStoppedTime  
-Xloggc:c:/memoryLeakDemoGc.log
 * 
 * @author lwkjob
 *
 */
public class OOMTest {
	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		while(true){
			list.add("走你快溢出");
		}
//		ByteBuffer.allocateDirect(257*1024*1024);
	}
}
