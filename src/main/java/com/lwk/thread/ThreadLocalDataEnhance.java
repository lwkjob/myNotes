package com.lwk.thread;

import java.util.Date;

/**
 * @author lwk 
 * 
 * 面向对象的设计 线程内共享数据
 */
public class ThreadLocalDataEnhance {
	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					synchronized (ThreadLocalDataEnhance.class) {
						ThreadScopeDataBean bean=ThreadScopeDataBean.getInstance();
						bean.setName("小m" + new Date().getTime());
						bean.setSex("nv" + new Date().getTime());
						new A().get();
						new B().get();
					}
				}
			}).start();
		}
	}

	static class A {
		public void get() {
			ThreadScopeDataBean bean = ThreadScopeDataBean.getInstance();
			System.out.println(Thread.currentThread().getName() + " beanA " + bean.getName() + ":" + bean.getSex());
		}
	}

	static class B {
		public void get() {
			ThreadScopeDataBean bean = ThreadScopeDataBean.getInstance();
			System.out.println(Thread.currentThread().getName() + " beanB " + bean.getName() + ":" + bean.getSex());
		}
	}

	static class ThreadScopeDataBean {
		private ThreadScopeDataBean() {
		};

		// 饱汉式单例
		// private static ThreadScopeDataBean dataBean=new
		// ThreadScopeDataBean();
		//
		// public static ThreadScopeDataBean getInstance(){
		// return dataBean;
		// }

		// 饿汉试单例
		// private static ThreadScopeDataBean dataBean = null;
		//
		// public static synchronized ThreadScopeDataBean getInstance() {
		//
		// if (dataBean == null) {
		// return new ThreadScopeDataBean();
		// }
		// return dataBean;
		// }

		public static ThreadLocal<ThreadScopeDataBean> threadLocalBeanMap = new ThreadLocal<ThreadLocalDataEnhance.ThreadScopeDataBean>();

		public static ThreadScopeDataBean getInstance() {
			ThreadScopeDataBean instance = threadLocalBeanMap.get();
			if (instance == null) {
				instance = new ThreadScopeDataBean();
				threadLocalBeanMap.set(instance);
			}
			return instance;
		}

		private String name;
		private String sex;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

	}
}
