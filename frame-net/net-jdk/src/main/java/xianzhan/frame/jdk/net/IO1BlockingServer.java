package xianzhan.frame.jdk.net;

import xianzhan.frame.jdk.net.util.IOUtil;

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

            var responseBytes = IOUtil.responseByte(IO1BlockingServer.class);

            out.write(responseBytes);
            out.flush();
        }
    }
}
