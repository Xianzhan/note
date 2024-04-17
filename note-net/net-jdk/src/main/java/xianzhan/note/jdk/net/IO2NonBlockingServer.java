package xianzhan.note.jdk.net;

import xianzhan.note.jdk.net.util.IOUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class IO2NonBlockingServer {

    public static void main() throws Exception {
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

            var responseBytes = IOUtil.responseByte(IO2NonBlockingServer.class);
            buff = ByteBuffer.wrap(responseBytes);

            socketChannel.write(buff);
            socketChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
