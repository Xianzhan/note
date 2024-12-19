package xianzhan.note.net.java.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileServer {

    public static void main() throws IOException {
        final int port = 8080;
        System.out.println("服务已启动 http://127.0.0.1:" + port);

        try (var serverSocket = new ServerSocket(port)) {
            while (Thread.currentThread().isAlive()) {
                try (var clientSocket = serverSocket.accept()) {
                    System.out.println("客户端连接: " + clientSocket);

                    BufferedReader requestHeaderReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String requestLine = requestHeaderReader.readLine();
                    if (requestLine == null) {
                        // 过滤错误请求
                        continue;
                    }
                    System.out.println("请求行: " + requestLine);
                    // GET /foo.html HTTP/1.1
                    String[] requestParts = requestLine.split(" ");
                    String requestPath = requestParts[1];
                    if ("/".equals(requestPath)) {
                        requestPath = "";
                    } else if (requestPath.startsWith("/") && requestPath.length() > 1) {
                        requestPath = requestPath.substring(1);
                    }

                    // 路径
                    Path path = Path.of(requestPath);
                    response(clientSocket, path);
                }
            }
        }
    }

    private static void response(Socket clientSocket, Path path) throws IOException {
        if (Files.notExists(path)) {
            System.out.println(Path.of("").toAbsolutePath());
            System.out.println("请求路径不存在: " + path);
            response404(clientSocket);
        }

        if (Files.isDirectory(path)) {
            responseDir(clientSocket, path);
            return;
        }

        responseFile(clientSocket, path);
    }

    private static void response404(Socket clientSocket) throws IOException {
        var writer = new PrintWriter(clientSocket.getOutputStream());

        // 响应头
        var responseFormatter = """
                HTTP/1.1 404 Not Found
                Content-Type: text/html
                Content-Length: %d
                
                %s
                """;
        var responseBody = """
                <html>
                  <body>
                    <h1>404 Not Found</h1>
                  </body>
                </html>
                """;
        // 都是 ascii 可以直接使用 length
        var response = responseFormatter.formatted(responseBody.length(), responseBody);
        writer.print(response);
        writer.flush();
    }

    private static void responseDir(Socket clientSocket, Path path) throws IOException {
        // TODO 目录

        // 响应头
        var responseFormatter = """
                HTTP/1.1 200 OK
                Content-Type: text/html
                Content-Length: %d
                
                """;
        var htmlBuilder = new StringBuilder();
        htmlBuilder.append("""
                <html>
                  <body>
                    <ul>
                """);

        try (var stream = Files.list(path)) {
            stream.forEach(p -> {
                htmlBuilder.append("<li>");
                htmlBuilder.append(p);
                htmlBuilder.append("</li>\n");
            });
        }

        htmlBuilder.append("""
                    </ul>
                  </body>
                </html>
                """);
        var html = htmlBuilder.toString().getBytes(StandardCharsets.UTF_8);

        var os = clientSocket.getOutputStream();
        var writer = new PrintWriter(os);
        writer.print(responseFormatter.formatted(html.length));
        writer.flush();

        os.write(html);
        os.flush();
    }

    private static void responseFile(Socket clientSocket, Path path) {
        // TODO 文件
    }
}