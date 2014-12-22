package com.gatesocket.client;

import com.gatesocket.channel.stream.SimpleStreamParser;
import com.gatesocket.channel.transport.socket.server.NioSocketChannelServerFactory;

public class EbcSocketServer
{
  public void start()
  {
    NioSocketChannelServerFactory niochannelserver = new NioSocketChannelServerFactory();
    niochannelserver.setPort(8009);
    niochannelserver.setPoolSize(20);
    niochannelserver.setStreamParser(new SimpleStreamParser());
    try {
      niochannelserver.afterPropertiesSet();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    niochannelserver.start();
  }
  public static void main(String[] args) {
	new EbcSocketServer().start();
}
}