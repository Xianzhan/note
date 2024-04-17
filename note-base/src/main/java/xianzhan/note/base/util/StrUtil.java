package xianzhan.note.base.util;

/**
 * 字符串工具类
 *
 * @author xianzhan
 * @since 2023-04-26
 */
public class StrUtil {

    /**
     * 使用 KMP 算法查找子串位置
     *
     * @param str     主串
     * @param pattern 子串
     * @return 子串在主串的位置
     */
    public static int indexOfKmp(String str, String pattern) {
        if (str == null || pattern == null) {
            return -1;
        }
        if (pattern.isEmpty()) {
            return 0;
        }

        final var next = indexOfKmpNext(pattern);

        final var strLen = str.length();
        final var patternLen = pattern.length();
        var strIndex = 0;
        var patternIndex = 0;
        // strIndex 永远不递减
        while (strIndex < strLen) {
            if (str.charAt(strIndex) == pattern.charAt(patternIndex)) {
                // 字符匹配, 指针后移
                strIndex++;
                patternIndex++;
            } else if (patternIndex > 0) {
                // 匹配失败, 根据 next 跳过字串前面的一些字符
                patternIndex = next[patternIndex - 1];
            } else {
                // 字串第一个字符不匹配
                strIndex++;
            }

            if (patternIndex == patternLen) {
                // 匹配, 返回开始索引
                return strIndex - patternIndex;
            }
        }

        return -1;
    }

    private static int[] indexOfKmpNext(String pattern) {
        final var len = pattern.length();
        final var next = new int[len];
        // 前缀相同字符的长度
        var prefixIndex = 0;
        // 第一字符默认为 0, 从 1 开始, 只增不减
        var i = 1;
        while (i < len) {
            if (pattern.charAt(prefixIndex) == pattern.charAt(i)) {
                // 前缀相同
                next[i++] = ++prefixIndex;
            } else {
                // 前缀不相同
                if (prefixIndex == 0) {
                    next[i++] = 0;
                } else {
                    // 已有前缀相同, 则往前移位, 直至为 0
                    prefixIndex = next[prefixIndex - 1];
                }
            }
        }

        return next;
    }
}
