package xianzhan.note.jdk.net.util;

import java.nio.charset.StandardCharsets;

public class IOUtil {

    public static byte[] responseByte(Class<?> c, String msg) {
        // https://zh.wikipedia.org/wiki/%E8%B6%85%E6%96%87%E6%9C%AC%E4%BC%A0%E8%BE%93%E5%8D%8F%E8%AE%AE
        var httpResponse = """
                HTTP/1.1 200 OK
                Server: %s
                Content-Type: text/html; charset=utf-8
                Content-Length: %d
                                       
                %s
                """.formatted(c.getSimpleName(),
                msg.getBytes(StandardCharsets.UTF_8).length,
                msg);
        return httpResponse.getBytes(StandardCharsets.UTF_8);
    }

    public static byte[] responseByte(Class<?> c) {
        return responseByte(c, "Hello world");
    }
}
