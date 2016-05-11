package com.lwk.socket.netty.simple;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * Created by liwenke on 16/5/4.
 */
public class TcpServerNetty {
    public static void main(String[] args) {
        EventLoopGroup boos = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .group(boos, worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // 创建一个16个线程的线程组来处理耗时的业务逻辑
                        @Override
                        public void initChannel(SocketChannel sch) {
                            sch.pipeline()
                                    //按照行分割消息(处理边界问题第一种方法)
//                                    .addLast(new LineBasedFrameDecoder(80))
                                    //按照固定长度接收消息(处理边界问题第二种方法)
                                    .addLast(new LengthFieldBasedFrameDecoder(80, 0, 4, 0, 4))
                                    //按照UTF-8转换字符串
                                    .addLast(new StringDecoder(CharsetUtil.UTF_8))
                                    .addLast(new TcpServerHandler());

                        }
                    });
            ChannelFuture f = serverBootstrap.bind(8080).sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            System.out.println("报错了");
        } finally {
            worker.shutdownGracefully();
            boos.shutdownGracefully();
        }
    }


}

class TcpServerHandler extends ChannelInboundHandlerAdapter {

    private int counter = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("通道激活");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("通道失效");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("报错了");
        cause.printStackTrace();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            System.out.println("channelRead: " + msg);
//           System.out.println(msg.equals("bye\r\n"));

            byte[] responseMsg =("你好啊客户端"+(counter++)+" \r\n").getBytes("UTF-8");
            ByteBuf out = ctx.alloc().buffer(responseMsg.length);
            out.writeBytes("我还要接着写".getBytes("UTF-8"));
            out.writeBytes(responseMsg);
            ChannelFuture channelFuture = ctx.writeAndFlush(out).sync();
            channelFuture.addListener(new ChannelFutureListener() {
                // write操作完成后调用的回调函数
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) { // 是否成功
                        System.out.println("write操作成功");
                    } else {
                        System.out.println("write操作失败");
                    }
                    if ("bye".equals(msg)){
                        ctx.close(); // 如果需要在write后关闭连接，close应该写在operationComplete中。注意close方法的返回值也是ChannelFuture
                    }
                }
            });
        } finally {
            ReferenceCountUtil.release(msg);
        }

    }
}
