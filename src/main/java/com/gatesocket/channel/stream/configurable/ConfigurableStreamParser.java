package com.gatesocket.channel.stream.configurable;

import java.io.InputStream;

import org.w3c.dom.Element;

import com.gatesocket.channel.stream.AbstractStreamParser;
import com.gatesocket.channel.stream.RequestHeader;
import com.gatesocket.channel.stream.ResponseHeader;
import com.gatesocket.channel.stream.SimpleHeader;

/**
 * 
 * @Creator Fx
 * @Create-Date 2011-5-11
 */
public class ConfigurableStreamParser extends AbstractStreamParser {

	private String requestConfigLocation;

	private String responseConfigLocation;

	protected Element requestRoot;

	protected Element responseRoot;

	private String charset = "UTF-8";
	
	protected int length = 4;

	@Override
	protected RequestHeader newRequestHeader() {
		return new SimpleHeader(this.length);
	}

	@Override
	protected ResponseHeader newResponseHeader() {
		return new SimpleHeader(this.length);
	}

}
