package xianzhan.frame.netty.echo.client;

import xianzhan.frame.netty.base.client.BaseClient;

import java.util.Collections;

public class EchoClient extends BaseClient {

    public EchoClient() {
        EchoClientHandler handler = new EchoClientHandler();
        setInboundHandlerList(Collections.singletonList(handler));
    }
}
