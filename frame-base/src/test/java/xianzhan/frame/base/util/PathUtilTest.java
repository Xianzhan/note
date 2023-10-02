package xianzhan.frame.base.util;

import org.junit.jupiter.api.Test;

public class PathUtilTest {

    @Test
    public void testList() {
        PathUtil.list(".").forEach(System.out::println);
    }
}
