package xianzhan.note.jdk.net;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Java 多路复用
 *
 * @author xianzhan
 * @since 2022-12-11
 */
public class IO3SelectorServer {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(80));
        // 非阻塞
        serverSocket.configureBlocking(false);

        // 创建 selector
        Selector selector = Selector.open();
        // 注册 accept 事件
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端启动");
        try (serverSocket; selector) {
            for (; ; ) {
                // 阻塞等待需要处理的事件
                selector.select();
                // 发生事件的集合
                Set<SelectionKey> eventKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = eventKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey event = iterator.next();
                    if (event.isAcceptable()) {
                        SocketChannel client;
                        try (ServerSocketChannel server = (ServerSocketChannel) event.channel()) {
                            client = server.accept();
                        }
                        client.configureBlocking(false);
                        // 注册读事件
                        client.register(selector, SelectionKey.OP_READ);
                        System.out.println("客户端连接成功");
                    } else if (event.isReadable()) {
                        // 读事件发生
                        int read;
                        ByteBuffer allocate = ByteBuffer.allocate(256);
                        try (SocketChannel client = (SocketChannel) event.channel()) {
                            read = client.read(allocate);
                        }
                        if (read == -1) {
                            System.out.println("未有数据到达，关闭客户端");
                        } else {
                            String msg = new String(allocate.array());
                            System.out.println(msg);
                        }
                    } else {
                        System.out.println("未处理事件: " + event);
                    }

                    // 移除此次事件
                    iterator.remove();
                }
            }
        }
    }
}
