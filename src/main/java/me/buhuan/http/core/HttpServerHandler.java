package me.buhuan.http.core;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LANGUAGE;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;

public class HttpServerHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext context, Object msg) {
		FullHttpResponse response =
			new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
				Unpooled.wrappedBuffer("OK".getBytes()));
		response.headers()
			.set(CONTENT_TYPE, "text/plain")
			.add(CONTENT_LANGUAGE, response.content().readableBytes())
			.add(CONNECTION, HttpHeaderValues.KEEP_ALIVE);
		context.write(response);
		context.flush();
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext context) {
		context.flush();
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
		context.close();
	}
}
