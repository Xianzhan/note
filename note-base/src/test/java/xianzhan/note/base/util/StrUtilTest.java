package xianzhan.note.base.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 字符串工具类测试
 *
 * @author xianzhan
 * @since 2023-04-26
 */
public class StrUtilTest {

    @Test
    public void testIndexOfKmp() {
        var str = """
                The Zen of Python, by Tim Peters
                Beautiful is better than ugly.
                Explicit is better than implicit.
                Simple is better than complex.
                Complex is better than complicated.
                Flat is better than nested.
                Sparse is better than dense.
                Readability counts.
                Special cases aren't special enough to break the rules.
                Although practicality beats purity.
                Errors should never pass silently.
                Unless explicitly silenced.
                In the face of ambiguity, refuse the temptation to guess.
                There should be one-- and preferably only one --obvious way to do it.
                Although that way may not be obvious at first unless you're Dutch.
                Now is better than never.
                Although never is often better than *right* now.
                If the implementation is hard to explain, it's a bad idea.
                If the implementation is easy to explain, it may be a good idea.
                Namespaces are one honking great idea -- let's do more of those!""";

        Assertions.assertEquals(-1, StrUtil.indexOfKmp(null, null));
        Assertions.assertEquals(-1, StrUtil.indexOfKmp(null, "The"));
        Assertions.assertEquals(-1, StrUtil.indexOfKmp(str, null));
        Assertions.assertEquals(-1, StrUtil.indexOfKmp(str, "-1"));
        Assertions.assertEquals(0, StrUtil.indexOfKmp(str, ""));
        Assertions.assertEquals(0, StrUtil.indexOfKmp(str, "The"));
        Assertions.assertEquals(11, StrUtil.indexOfKmp(str, "Python"));
        Assertions.assertEquals(26, StrUtil.indexOfKmp(str, "Peters"));
        Assertions.assertEquals(33, StrUtil.indexOfKmp(str, "Beautiful"));
    }

    @Test
    public void testStr() {
        var i = 10;
        var s = "abc";
        var str = STR. "i: \{ i }, s: \{ s }" ;
        Assertions.assertEquals("i: 10, s: abc", str);

        str = STR. """
                i:\{ i },s:\{ s }""" ;
        Assertions.assertEquals("i:10,s:abc", str);
    }
}
