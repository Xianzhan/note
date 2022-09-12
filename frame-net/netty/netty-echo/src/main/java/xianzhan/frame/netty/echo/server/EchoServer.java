package xianzhan.frame.netty.echo.server;

import io.netty.channel.ChannelInboundHandler;
import xianzhan.frame.netty.base.server.BaseServer;

import java.util.Collections;
import java.util.List;

/**
 * echo 服务
 */
public class EchoServer extends BaseServer {

    public EchoServer(int port, List<ChannelInboundHandler> inboundHandlerList) {
        super(port, inboundHandlerList);
    }

    public static void main(String[] args) throws Exception {
        EchoServer echoServer = new EchoServer(80, Collections.singletonList(new EchoServerHandler()));
        echoServer.start();
    }
}
