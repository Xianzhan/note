package xianzhan.note.netty.base.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import xianzhan.note.base.util.CollUtil;

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
        // 主从多线程 Reactor 模型

        // bossGroup 线程池处理 accept 事件
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // workerGroup 线程池处理 read/write 事件
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 服务器
            ServerBootstrap b = new ServerBootstrap();
            // 设置线程组
            b.group(bossGroup, workerGroup)
                    // 配置 server channel 处理 accept 事件/io.netty.channel.socket.ServerSocketChannel
                    // |Common	               |Linux	                 |Mac
                    // |NioEventLoopGroup	   |EpollEventLoopGroup	     |KQueueEventLoopGroup
                    // |NioEventLoop	       |EpollEventLoop	         |KQueueEventLoop
                    // |NioServerSocketChannel |EpollServerSocketChannel |KQueueServerSocketChannel
                    // |NioSocketChannel	   |EpollSocketChannel	     |KQueueSocketChannel
                    .channel(NioServerSocketChannel.class)
                    // ====== Netty 参数 ======
                    //
                    // ALLOCATOR: ByteBuf 的分配器 ByteBufAllocator. 可选值(Netty4.x):
                    //            1. ByteBufAllocator.DEFAULT
                    //            2. PooledByteBufAllocator.DEFAULT
                    //            3. UnpooledByteBufAllocator.DEFAULT
                    // RCVBUF_ALLOCATOR: 用于 Channel 分配接受 Buffer 的分配器,
                    //                   1. AdaptiveRecvByteBufAllocator.DEFAULT, 是一个自适应的接受缓冲区分配器，能根据接受到的数据自动调节大小.
                    //                   2. FixedRecvByteBufAllocator, 固定大小的接受缓冲区分配器.
                    // CONNECT_TIMEOUT_MILLIS： 连接超时毫秒数, default 30000
                    // WRITE_SPIN_COUNT: 一个 Loop 写操作执行的最大次数.
                    //                   默认值为 16, 也就是说, 对于大数据量的写操作至多进行 16 次, 如果 16 次仍没有全部写完数据,
                    //                   此时会提交一个新的写任务给 EventLoop, 任务将在下次调度继续执行.
                    //                   这样, 其他的写请求才能被响应不会因为单个大数据量写请求而耽误.
                    // WRITE_BUFFER_WATER_MARK: 水位线, 提用户当前通道的消息堆积情况.
                    //                          用于替代原有的高低水位线参数, 高水位线和低水位线是字节数.
                    // ALLOW_HALF_CLOSURE: 一个连接的远端关闭时本地端是否关闭.
                    //                     默认 false, 连接自动关闭;
                    //                     为 true 时, 触发 ChannelInboundHandler 的 userEventTriggered() 方法, 事件为 ChannelInputShutdownEvent.
                    //
                    // ====== TCP 参数 ======
                    //
                    // SO_KEEPALIVE: 是否启用心跳保活机制, 即连接保活.
                    //               默认 false; 启用该功能时, TCP 会主动探测空闲连接的有效性.
                    // SO_SNDBUF: 操作系统内核的写缓冲区, 所有应用程序需要发送到对端的信息, 都会放到该缓冲区中, 等待发往对端.
                    //            默认 16K
                    // SO_RCVBUF: 操作系统内核的读缓冲区, 所有对端发过来的数据都会放到该缓冲区, 等待应用程序取走.
                    //            默认 86K
                    // SO_REUSEADDR: 地址复用.
                    //               默认 false
                    // SO_LINGER: 关闭 socket 的延迟时间
                    //            1. -1 默认, 表示 socket.close() 方法立即返回, 但 OS 底层会将发送缓冲区的数据全部发送到对端
                    //            2. 0, 表示 socket.close() 方法立即返回, OS 放弃发送缓冲区的数据直接向对端发送 RST 包, 对端收到复位错误
                    //            3. 非 0 整数值, 表示调用 socket.close() 方法的线程被阻塞直到延迟时间到或发送缓冲区中的数据发送完毕, 若超时, 则对端会收到复位错误
                    // SO_BACKLOG: 服务端处理客户端连接请求是顺序处理的, 所以同一时间只能处理一个客户端连接.
                    //             多个客户端来的时候, 服务端将不能处理的客户端连接请求放在队列中等待处理，backlog 参数指定了队列的大小
                    //             windows 200, 其余 128, 若并发较高可以设置较大值
                    // SO_TIMEOUT: 超时时间.
                    // TCP_NODELAY: 用于启用或关闭 Nagle 算法.
                    //              Netty 默认为 true, 关闭缓存立即发送数据提高实时性.
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 设置服务端的 handler
                    .handler(new LoggingHandler(LogLevel.INFO))
                    // 设置客户端的 handler
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // ChannelInitializer 是用于当 SocketChannel 成功注册到绑定的 Reactor 上后，
                        // 用于初始化该 SocketChannel 的 Pipeline。
                        // 它的 initChannel 方法会在注册成功后执行。
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            if (CollUtil.isNotEmpty(inboundHandlerList)) {
                                inboundHandlerList.forEach(p::addLast);
                            }
                        }
                    });

            // 绑定端口地址，开始监听客户端事件（OP_ACCEPT）
            ChannelFuture f = b.bind(port).sync();
            // 等待服务 NioServerSocketChannel 关闭
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
