package me.buhuan.http.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;


public class HttpServer {

	public void start(int port) throws InterruptedException {
		EventLoopGroup parentGroup = new NioEventLoopGroup();
		EventLoopGroup childGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(parentGroup, childGroup)
				.channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 128)
				.childOption(ChannelOption.SO_KEEPALIVE, true)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) {
						ChannelPipeline pipeline = ch.pipeline();
						pipeline.addLast(new HttpResponseEncoder())
							.addLast(new HttpRequestDecoder())
							.addLast(new HttpServerHandler());
					}
				});
			ChannelFuture future = bootstrap.bind(port).sync();
			future.channel().closeFuture().sync();
		} finally {
			parentGroup.shutdownGracefully().sync();
			childGroup.shutdownGracefully().sync();
		}
	}
}
