package com.cmcc.slience.netty.v0;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClinet {

	public void connet(String host, int port) throws Exception {

		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap strap = new Bootstrap();
			strap.group(group)
				.channel(NioSocketChannel.class)
				.option(ChannelOption.TCP_NODELAY, true)
				.handler(new ChannelInitializer<Channel>() {
					@Override
					protected void initChannel(Channel channel) throws Exception {
						channel.pipeline().addLast(new TimeClientHandler());
					}
				});
				
			//发起异步连接操作
			ChannelFuture future = strap.connect(host, port).sync();
			//等待客户端链路关闭
			future.channel().closeFuture().sync();	
		} finally {
			group.shutdownGracefully();
		}
	}
}
