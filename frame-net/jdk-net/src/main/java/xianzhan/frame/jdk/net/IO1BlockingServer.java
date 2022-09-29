package xianzhan.frame.jdk.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Java 同步阻塞 IO 服务器
 *
 * @author xianzhan
 * @since 2022-09-28
 */
public class IO1BlockingServer {

    public static void main(String[] args) throws IOException {
        try (ExecutorService threadPool = Executors.newCachedThreadPool();
             var serverSocket = new ServerSocket(80)
        ) {
            System.out.println("服务启动成功，等待请求...");

            Socket accept;
            // accept 同步阻塞等待客户端连接
            while ((accept = serverSocket.accept()) != null) {
                Socket client = accept;
                threadPool.execute(() -> handleAccept(client));
            }

            System.out.println("请求处理完成，等待下一个请求~");
        }
    }

    private static void handleAccept(Socket client) {
        try (var in = client.getInputStream();
             var out = client.getOutputStream()
        ) {

            handleRequestAndResponse(in, out);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleRequestAndResponse(InputStream in, OutputStream out) throws IOException {
        var buff = new byte[in.available()];
        while (in.read(buff) != -1) {
            var line = new String(buff, StandardCharsets.UTF_8);
            System.out.println(line);

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

            out.write(responseBytes);
            out.flush();
        }
    }
}
