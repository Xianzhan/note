package xianzhan.note.net.hutool;

import cn.hutool.http.HttpUtil;

/**
 * @author xianzhan
 * @since 2023-05-27
 */
public class Main {

    public static void main() {
        String html = HttpUtil.get("https://www.baidu.com");
        System.out.println(html);
    }
}
