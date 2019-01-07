package tools.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		NioEventLoopGroup group = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(group,workerGroup).channel(NioServerSocketChannel.class).localAddress(1124)
				.childHandler(new ChannelInitializer<Channel>() {

					@Override
					protected void initChannel(Channel ch) throws Exception {
						// TODO Auto-generated method stub
						ch.pipeline().addLast(new EchoServerHandler());
					}
				});
			ChannelFuture future = bootstrap.bind(1124).sync();
			System.out.println("echo Server started and listen on " + future.channel().localAddress());
			future.channel().closeFuture().sync();
		} finally {
			// TODO: handle finally clause
			group.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

}
