package xianzhan.frame.netty.base.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import xianzhan.frame.base.util.CollUtil;

import java.util.List;

public abstract class BaseServer {

    /**
     * 监听端口号
     */
    private final int                         port;
    /**
     * 回调事件
     */
    private final List<ChannelInboundHandler> inboundHandlerList;

    public void start() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            if (CollUtil.isNotEmpty(inboundHandlerList)) {
                                inboundHandlerList.forEach(p::addLast);
                            }
                        }
                    });

            // 启动服务
            ChannelFuture f = b.bind(port).sync();
            // 等待服务 socket 关闭
            f.channel().closeFuture().sync();
        } finally {
            // 关闭所有事件循环以终止所有线程
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public BaseServer(int port, List<ChannelInboundHandler> inboundHandlerList) {
        this.port = port;
        this.inboundHandlerList = inboundHandlerList;
    }
}
