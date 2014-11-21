package com.gatesocket.channel.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gatesocket.utils.BytesUtil;
import com.gatesocket.session.Session;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-30
 */
public abstract class AbstractStreamParser implements StreamParser {

	protected String charset = "UTF-8";

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public byte[] joinRequestStream(Session session, Stream stream) {
		RequestHeader header = (RequestHeader) stream.getHeader();
		byte[] msg = stream.getMessage();
		header.setBodyLength(msg.length);
		return BytesUtil.join(header.getHeaderContent(), msg);
	}

	public byte[] joinResponseStream(Session session, Stream stream) {
		ResponseHeader header = (ResponseHeader) stream.getHeader();
		byte[] msg = stream.getMessage();
		header.setBodyLength(msg.length);
		return BytesUtil.join(header.getHeaderContent(), msg);
	}

	public RequestHeader buildRequestHeader(Map<String, String> map) {
		RequestHeader header = newRequestHeader();
		header.fromMap(map);
		return header;
	}

	public ResponseHeader buildResponseHeader(Map<String, String> map) {
		ResponseHeader header = newResponseHeader();
		header.fromMap(map);
		return header;
	}

	public List<Stream> parseRequestStream(Session session, byte[] msg) {
		List<Stream> retVal = new ArrayList<Stream>();

		byte[] left = (byte[]) session.getAttribute(STREAM_ATTACHMENT_REQUST);
		if (left == null) {
			left = new byte[] {};
		}
		msg = BytesUtil.join(left, msg);
		session.setAttribute(STREAM_ATTACHMENT_REQUST, msg);

		RequestHeader header = newRequestHeader();
		if (msg.length < header.getLength()) {
			return retVal;
		}

		// 发现报文头，进行处理
		byte[] headerBytes = BytesUtil.read(msg, header.getLength());

		header.parse(headerBytes);

		left = BytesUtil.remove(msg, header.getLength());

		if (left.length < header.getBodyLength()) {
			return retVal;
		} else {
			byte[] body = BytesUtil.read(left, header.getBodyLength());
			Stream temp = new Stream(header, body);
			retVal.add(temp);
			left = BytesUtil.remove(left, header.getBodyLength());
			session.setAttribute(STREAM_ATTACHMENT_REQUST, left);
			// 递归读取，支持异步长连接不间断发送，在同步短链接中，递归读取将不会获得数�?
			retVal.addAll(this.parseRequestStream(session, new byte[] {}));
		}

		return retVal;
	}

	public List<Stream> parseResponseStream(Session session, byte[] msg) {
		List<Stream> retVal = new ArrayList<Stream>();

		byte[] left = (byte[]) session.getAttribute(STREAM_ATTACHMENT_RESPONSE);
		if (left == null) {
			left = new byte[] {};
		}
		msg = BytesUtil.join(left, msg);
		session.setAttribute(STREAM_ATTACHMENT_RESPONSE, msg);

		ResponseHeader header = newResponseHeader();
		if (msg.length < header.getLength()) {
			return retVal;
		}

		// 发现报文头，进行处理
		byte[] headerBytes = BytesUtil.read(msg, header.getLength());

		header.parse(headerBytes);

		left = BytesUtil.remove(msg, header.getLength());

		if (left.length < header.getBodyLength()) {
			return retVal;
		} else {
			byte[] body = BytesUtil.read(left, header.getBodyLength());
			Stream temp = new Stream(header, body);
			retVal.add(temp);
			left = BytesUtil.remove(left, header.getBodyLength());
			session.setAttribute(STREAM_ATTACHMENT_RESPONSE, left);
			// 递归读取，支持异步长连接不间断发送，在同步短链接中，递归读取将不会获得数�?
			retVal.addAll(this.parseResponseStream(session, new byte[] {}));
		}

		return retVal;
	}

	protected abstract RequestHeader newRequestHeader();

	protected abstract ResponseHeader newResponseHeader();

}
