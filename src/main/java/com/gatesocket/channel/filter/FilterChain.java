package com.gatesocket.channel.filter;

import com.gatesocket.Transaction;

/**
 * 
 * @Creator Fx
 * @Create-Date 2010-5-12
 */
public interface FilterChain {

	void doFilter(Transaction transaction) throws FilterException;

}
