package xianzhan.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author xianzhan
 * @since 2020-07-14
 */
public class Main {

    public static void main(String[] args) throws Exception {
        var client = single();
        var keys = client.getKeys();
        for (String key : keys.getKeys()) {
            System.out.println(key);
        }

        var one = client.getBucket("one");
//        one.set(10);
        var val = one.get();
        System.out.println(val);

        client.shutdown();
    }

    private static RedissonClient single() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://172.18.14.133:6379");
//                .setTimeout(3000)
//                .setConnectionPoolSize(3)
///                .setConnectionMinimumIdleSize(1);

        return Redisson.create(config);
    }

//    public static void main(String[] args) throws IOException {
//        String host = "172.18.14.133";
//        int port = 6379;
//        Socket socket = new Socket(host, port);
//        socket.setSoTimeout(3000);
//        // 建立连接后获得IO出流
//        OutputStream outputStream = socket.getOutputStream();
//        InputStream inputStream = socket.getInputStream();
//        // 看过很多是将斜杠转义的写法，是不对的，因为他和r或者n连起来当做命令，而不是单纯的字符串
//        // redis在处理命令时也做了判断，直接发送redis cli命令也可以解析，但必须以\r\n或者\n结尾
//        // String message = "*3\\r\\n$3\\r\\nSET\\r\\n$6\\r\\nsocket\\r\\n$10\\r\\nsocketTest\\r\\n";
//        getResult(outputStream, inputStream, "*3\r\n$3\r\nSET\r\n$6\r\nsocket\r\n$10\r\nsocketTest\r\n");
//        getResult(outputStream, inputStream, "error test\r\n");
//        getResult(outputStream, inputStream, "*2\r\n$3\r\nget\r\n$5\r\nsocket\r\n");
//        getResult(outputStream, inputStream, "set socket socketTest1\r\n");
//        getResult(outputStream, inputStream, "exists socket\r\n");
//        getResult(outputStream, inputStream, "get socket\r\n");
//        getResult(outputStream, inputStream, "keys *\r\n");
//        getResult(outputStream, inputStream, "get otherTest\n");
//        socket.close();
//        System.err.println("*****socket closed*****");
//    }


    private static void getResult(OutputStream outputStream, InputStream inputStream, String cmd) throws IOException {
        System.err.println("\r\nsend command : " + cmd.replaceAll("\\r\\n", " "));
        outputStream.write(cmd.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        // 此写法是为了测试，不适合返回数据太多的情况
        byte[] bytes = new byte[128];
        int length = inputStream.read(bytes);
        String result = new String(bytes, 0, length, StandardCharsets.UTF_8);
        while (result.length() > 0) {
            int index = result.indexOf("\r\n");
            // 取第一个命令
            String currentStr = result.substring(0, index);
            // 剩余命令，注意：\r\n占两个长度
            result = result.substring(index + 2);
            if (currentStr.startsWith("+")) {
                System.err.println("+ status reply message：" + currentStr.substring(1));
            } else if (currentStr.startsWith("-")) {
                System.err.println("- error reply message：" + currentStr.substring(1));
            } else if (currentStr.startsWith(":")) {
                System.err.println(": integer reply message：" + currentStr.substring(1));
            } else if (currentStr.startsWith("$")) {
                System.err.println("$ bulk reply message：" + currentStr.substring(1));
            } else if (currentStr.startsWith("*")) {
                System.err.println("* multi bulk reply message：" + currentStr.substring(1));
            } else {
                System.err.println("other message : " + currentStr);
            }
        }
    }
}
