package com.gatesocket.channel.transport;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

//import org.springframework.beans.factory.BeanNameAware;

import com.gatesocket.utils.ClassUtil;
import com.gatesocket.channel.Channel;
import com.gatesocket.channel.ChannelException;
import com.gatesocket.channel.ChannelPool;
import com.gatesocket.session.SessionContextListener;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-8
 */
public abstract class KeepAliveChannelPool implements ChannelPool, Runnable {


	protected int poolSize = 1;

	protected BlockingQueue<Channel> channels = new ArrayBlockingQueue<Channel>(this.poolSize);

	protected int number = 0;

	protected volatile boolean state = false;

	protected String name = "KeepAliveChannelPool-DEFAULT";

	protected List<SessionContextListener> listeners = new ArrayList<SessionContextListener>();

	protected String charset = "UTF-8";

	public void init() {
		System.out.println("initializing keep alive channel pool ...");
		try {
			for (int i = 0; i < poolSize; i++) {
				Channel chl = createChannel();
//				chl.connect();
				this.put(chl);
				this.increase();
			}
		} catch (Throwable tr) {
//			System.out.println(tr.getMessage(), tr);
			System.out.println(tr.getMessage());
		}
		state = true;
		new Thread(this).start();
	}

	public void destroy() {
		try {
			this.state = false;
			System.out.println("destroy keep alive channel pool ...");
			for (int i = 0; i < poolSize; i++) {
				System.out.println("destroy channel[" + i + "]");
				this.channels.take().close();
				this.decrease();
			}
		} catch (Throwable tr) {
//			System.out.println(tr.getMessage(), tr);
			System.out.println(tr.getMessage());
		}
	}

	public void setPoolSize(int poolSize) {
		if (!this.state) {
			this.poolSize = poolSize;
			this.number = 0;
			this.channels = new ArrayBlockingQueue<Channel>(this.poolSize);
		} else {
			System.out.println("KeepAliveChannelPool[" + name + "] is running,please stop to set pool size");
		}
	}

	protected void put(Channel channel) {
		try {
			this.channels.offer(channel, 10 * 1000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			System.out.println("cannot put channel to channel pool,please check error");
			System.out.println("current channel will be discarded");
			try {
				channel.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	protected void decrease() {
		synchronized (this) {
			this.number--;
		}
	}

	protected void increase() {
		synchronized (this) {
			this.number++;
		}
	}

	public Channel getChannel() {
		try {
			Channel chl = this.channels.take();
			Channel proxy = (Channel) Proxy.newProxyInstance(ClassUtil.getDefaultClassLoader(),
					new Class<?>[] { Channel.class }, new ProxyChannelHandler(this, chl));
			return proxy;
		} catch (Exception e) {
			throw new ChannelException("cannot establish connection to remote server", e);
		}
	}

	public void run() {
		while (state) {
			try {
//				if (logger.isDebugEnabled()) {
					StringBuffer buffer = new StringBuffer("\r\n" + this.toString());
					System.out.println(buffer.toString());
//				}

				Thread.sleep(30 * 1000);
				if (!this.state) {
					break;
				}
				synchronized (this) {
					if (number < this.poolSize) {
						for (int i = 0; i < (this.poolSize - number); i++) {
							Channel chl = createChannel();
							chl.connect();
							this.put(chl);
							this.increase();
						}
					}
				}
			} catch (Throwable tr) {
//				System.out.println(tr.getMessage(), tr);
				System.out.println(tr.getMessage());
			}
		}
	}

	public void setBeanName(String name) {
		this.name = name;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public void setListeners(List<SessionContextListener> listeners) {
		this.listeners = listeners;
	}

	protected Channel createChannel() {
		Channel channel = newChannel();
		channel.setName(this.name);
		channel.setCharset(this.charset);
//		for (Iterator<SessionContextListener> itr = this.listeners.iterator(); itr.hasNext();) {
//			channel.addSessionContextListener(itr.next());
//		}
		return channel;
	}

	protected abstract Channel newChannel();

	static class ProxyChannelHandler implements java.lang.reflect.InvocationHandler {

		private KeepAliveChannelPool channelPool;

		private Channel channel;

		public ProxyChannelHandler(KeepAliveChannelPool pool, Channel channel) {
			this.channel = channel;
			this.channelPool = pool;
		}

		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if (method.getName().equals("close")) {
				try {
					if (this.channel.isConnected()) {
						return null;
					} else {
						channel.connect();
						return null;
					}
				} finally {
					System.out.println("put this channel back channel pool");
					if (this.channel.isConnected()) {
						this.channelPool.put(channel);
					} else {
						System.out.println("Because channel cannot keep connect alive with remote server,this channel will be dropped");
						System.out.println("Channel Pool alive size will decrease!!!");
						this.channelPool.decrease();
					}
				}
			}
			try {
				return method.invoke(channel, args);
			} catch (Throwable tr) {
				try {
					this.channel.close();
				} finally {
					this.channel = this.channelPool.createChannel();
					// this.channel.connect();
				}
				throw tr;
			}
		}

	}
}
