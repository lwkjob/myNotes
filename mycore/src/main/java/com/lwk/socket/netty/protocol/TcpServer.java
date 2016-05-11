package com.lwk.socket.netty.protocol;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

public class TcpServer {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
                    b.group(bossGroup, workerGroup)
                     .channel(NioServerSocketChannel.class)
                     .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                            // 负责通过4字节Header指定的Body长度将消息切割
                            pipeline.addLast("frameDecoder",
                                    //   LengthFieldBasedFrameDecoder 构造方法介绍
                                    //  第一个参数为信息最大长度，  超过这个长度回报异常，
                                    //  第二参数为长度属性的起始（偏移）位，我们的协议中长度是0到第4个字节，所以这里写0，
                                    //  第三个参数为“长度属性”的长度，我们是4个字节，所以写4，
                                    //  第四个参数为长度调节值，在总长被定义为包含包头长度时， 修正信息长度，
                                    //  第五个参数为跳过的字节数，根据需要我们跳过前4个字节，以便接收端直接接受到不含“长度属性”的内容。
                                    //  至此，接收端会按照decoder指定的长度接收完整后才会调用handler继续处理信息
                                    new LengthFieldBasedFrameDecoder(1048576, 0, 4, 0, 4));

                            // 负责将frameDecoder处理后的完整的一条消息的protobuf字节码转成Student对象
                            pipeline.addLast("protobufDecoder",
                                    new ProtobufDecoder(StudentMsg.Student.getDefaultInstance()));

                            // 负责将写入的字节码加上4字节Header前缀来指定Body长度
                            pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));

                            // 负责将Student对象转成protobuf字节码
                            pipeline.addLast("protobufEncoder", new ProtobufEncoder());

                            pipeline.addLast(new TcpServerHandler());
                        }
                    });
            ChannelFuture f = b.bind(8080).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}