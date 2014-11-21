package com.gatesocket.channel.filter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gatesocket.Transaction;
import com.gatesocket.channel.ChannelException;
import com.gatesocket.channel.ChannelProcessor;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-12
 */
public class DefaultFilterChain implements FilterChain {

	private List<Filter> filters = new ArrayList<Filter>();

	private ChannelProcessor processor;

	private Iterator<Filter> itr = null;

	public DefaultFilterChain(List<Filter> filters, ChannelProcessor processor) {
		this.filters = new ArrayList<Filter>(filters);
		this.processor = processor;
		itr = this.filters.iterator();
	}

	public void addFilter(Filter filter) {
		filters.add(filter);
	}

	public void doFilter(Transaction transaction) throws FilterException {
		if (itr.hasNext()) {
			itr.next().doFilter(transaction, this);
		} else {
			try {
				this.processor.process(transaction);
			} catch (ChannelException e) {
				throw new FilterException(e);
			}

		}
	}

}
