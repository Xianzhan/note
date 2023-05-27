package xianzhan.frame.net.hutool;

import cn.hutool.http.HttpUtil;

/**
 * @author xianzhan
 * @since 2023-05-27
 */
public class Main {

    public static void main(String[] args) {
        String html = HttpUtil.get("https://www.baidu.com");
        System.out.println(html);
    }
}
