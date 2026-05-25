package xianzhan.note.cache.redisson;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RedissonUtilTest {

    @Test
    public void testSetGet() {
        RedissonUtil.set("one", 1);
        Integer one = RedissonUtil.get("one");
        Assertions.assertEquals(1, one);
    }
}
