package xianzhan.frame.netty.base.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import xianzhan.frame.base.util.CollUtil;

import java.util.List;

public abstract class BaseClient {

    private List<ChannelInboundHandler> inboundHandlerList;

    public void connect(String host, int port) throws Exception {
        if (CollUtil.isEmpty(inboundHandlerList)) {
            throw new IllegalAccessException("setInboundHandlerList");
        }

        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            inboundHandlerList.forEach(p::addLast);
                        }
                    });

            // 启动客户端
            ChannelFuture f = b.connect(host, port).sync();
            // 等待连接关闭
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    public void setInboundHandlerList(List<ChannelInboundHandler> inboundHandlerList) {
        this.inboundHandlerList = inboundHandlerList;
    }
}
