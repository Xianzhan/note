package xianzhan.frame.jdk.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class IO2NonBlockingServer {

    public static void main(String[] args) throws Exception {
        try (var threadPool = Executors.newCachedThreadPool();
             var serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.bind(new InetSocketAddress(80));
            serverSocketChannel.configureBlocking(false);

            for (; ; ) {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel == null) {
                    System.out.println("非阻塞，未有请求");
                    TimeUnit.MILLISECONDS.sleep(512);
                    continue;
                }

                threadPool.execute(() -> handleAccept(socketChannel));
            }
        }
    }

    private static void handleAccept(SocketChannel socketChannel) {
        try {

            var buff = ByteBuffer.allocate(8096);
            socketChannel.read(buff);

            buff.flip();
            var req = new String(buff.array(), StandardCharsets.UTF_8);
            System.out.println(req);

            var message = "Hello world";
            // https://zh.wikipedia.org/wiki/%E8%B6%85%E6%96%87%E6%9C%AC%E4%BC%A0%E8%BE%93%E5%8D%8F%E8%AE%AE
            var response = """
                HTTP/1.1 200 OK
                Server: %s
                Content-Type: text/html; charset=utf-8
                Content-Length: %d
                                       
                %s
                """.formatted(IO1BlockingServer.class.getSimpleName(),
                    message.getBytes(StandardCharsets.UTF_8).length,
                    message);
            var responseBytes = response.getBytes(StandardCharsets.UTF_8);
            buff = ByteBuffer.wrap(responseBytes);

            socketChannel.write(buff);
            socketChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
