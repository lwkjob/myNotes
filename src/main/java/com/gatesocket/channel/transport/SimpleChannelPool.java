package com.gatesocket.channel.transport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;


import com.gatesocket.utils.ClassUtil;
import com.gatesocket.channel.Channel;
import com.gatesocket.channel.ChannelException;
import com.gatesocket.channel.ChannelPool;
import com.gatesocket.session.SessionContextListener;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-9
 */
public abstract class SimpleChannelPool implements ChannelPool, Runnable {

//	protected static Log logger = LogFactory.getLog(SimpleChannelPool.class);

	private int poolSize = 1;

	private BlockingQueue<Channel> channels = new ArrayBlockingQueue<Channel>(this.poolSize);

	protected String name;

	protected List<SessionContextListener> listeners = new ArrayList<SessionContextListener>();

	protected String charset = "UTF-8";

	protected int number = 0;

	protected volatile boolean state = false;

	public void init() {
		try {
			System.out.println("initializing socket simple channel pool");
			for (int i = 0; i < poolSize; i++) {
				Channel chl = createChannel();
				this.put(chl);
				this.increase();
			}
		} catch (Throwable tr) {
			System.out.println(tr.getMessage());
		}

		state = true;
		new Thread(this).start();
	}

	public void destroy() {
		try {
			System.out.println("destroying socket simple channel pool ...");
			this.state = false;
			for (int i = 0; i < poolSize; i++) {
				if (channels.isEmpty()) {
					break;
				}
				Channel channel = this.channels.take();
				if (channel.isConnected()) {
					channel.close();
				}
				this.decrease();
			}
			System.out.println("destroy socket simple channel pool successfully...");
		} catch (Throwable tr) {
			System.out.println(tr.getMessage());
		}
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
		this.channels = new ArrayBlockingQueue<Channel>(this.poolSize);
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
				System.out.println(e1.getMessage());
			}
		}
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public Channel getChannel() {
		try {
			Channel chl = this.channels.take();

			Channel proxy = (Channel) Proxy.newProxyInstance(ClassUtil.getDefaultClassLoader(),
					new Class<?>[] { Channel.class }, new ProxyChannelHandler(this, chl));
			return proxy;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ChannelException("cannot establish connection to remote server", e);
		}
	}

	public void setBeanName(String name) {
		this.name = name;
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
							// chl.connect();
							this.put(chl);
							this.number++;
						}
					}
				}
			} catch (Throwable tr) {
				System.out.println(tr.getMessage());
			}
		}
	}

	protected abstract Channel newChannel();

	static class ProxyChannelHandler implements InvocationHandler {

		private SimpleChannelPool channelPool;

		private Channel channel;

		public ProxyChannelHandler(SimpleChannelPool pool, Channel channel) {
			this.channel = channel;
			this.channelPool = pool;
		}

		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if (method.getName().equals("close")) {
				try {
					if (this.channel.isConnected()) {
						this.channel.close();
					}
					return null;
				} finally {
					System.out.println("put this channel back channel pool");
					this.channelPool.put(channel);
				}
			}
			try {
				return method.invoke(channel, args);
			} catch (Throwable tr) {
				try {
					this.channel.close();
				} finally {
					this.channel = this.channelPool.createChannel();
				}
				throw tr;
			}
		}

	}
}
