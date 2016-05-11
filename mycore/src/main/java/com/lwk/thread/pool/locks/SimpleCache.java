package com.lwk.thread.pool.locks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * 简单多线程缓存实现
 * @author lwkjob
 *
 */
public class SimpleCache {
	Map<String,Object> cacheMap=new HashMap<String,Object>();
	
	public static void main(String[] args) {
		
	}
	
	
	private ReadWriteLock rwlock= new ReentrantReadWriteLock();
	public Object getValue(String name){
		rwlock.readLock().lock();
		Object value=cacheMap.get(name);
		try{
			if(value==null){
				rwlock.readLock().unlock();
				rwlock.writeLock().lock();
				if(value==null){//再次检查值是否为空，防止多个线程同时走到这里
					//开始查数据库
					value="valuelwk";
					rwlock.writeLock().unlock();
					rwlock.readLock().lock();
				}
			}
		}finally{
			rwlock.readLock().unlock();
		}
		return value;
	}
}
