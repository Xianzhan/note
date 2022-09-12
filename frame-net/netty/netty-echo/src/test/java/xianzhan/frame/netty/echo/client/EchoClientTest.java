package xianzhan.frame.netty.echo.client;

import org.junit.jupiter.api.Test;

public class EchoClientTest {

    @Test
    public void testConnect() throws Exception {
        EchoClient client = new EchoClient();
        client.connect("127.0.0.1", 80);
    }
}
