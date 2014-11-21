package com.gatesocket.channel.filter;

import com.gatesocket.Transaction;


/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-12
 */
public interface Filter {
	
	void doFilter(Transaction transaction, FilterChain chain) throws FilterException;
}
