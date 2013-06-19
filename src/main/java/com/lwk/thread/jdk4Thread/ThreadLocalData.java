package com.lwk.thread.jdk4Thread;

import java.util.Date;

/**
 * @author lwk
 *  用jdk的ThreadLocal类，实现线程范围内的数据共享
 */
public class ThreadLocalData {
	private static ThreadLocal<Long> dataLocal = new ThreadLocal<Long>();
	private static ThreadLocal<DataBean> dataLocalBean = new ThreadLocal<DataBean>();
	private static long data = 0;

	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					synchronized (ThreadLocalData.class) {
					//数据类型1
						data = new Date().getTime();
						dataLocal.set(data);
						System.out.println(Thread.currentThread().getName() + " 产生" + data);
					//数据类型2
						DataBean bean=new DataBean();
						bean.setName("小m"+data);
						bean.setSex("nv"+data);
						dataLocalBean.set(bean);
						new A().get();
						new B().get();
					}
				}
			}).start();
		}
	}

	static class A {
		public void get() {
			DataBean	bean=dataLocalBean.get();
			System.out.println(Thread.currentThread().getName() + " A " + dataLocal.get());
			System.out.println(Thread.currentThread().getName() + " beanA " + bean.getName()+":"+bean.getSex());
		}
	}

	static class B {
		public void get() {
			DataBean	bean=dataLocalBean.get();
			System.out.println(Thread.currentThread().getName() + " B " + dataLocal.get());
			System.out.println(Thread.currentThread().getName() + " beanB " + bean.getName()+":"+bean.getSex());
		}
	}

	static class DataBean {
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
