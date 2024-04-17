package xianzhan.note.base.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

public class CollUtilTest {

    @Test
    public void testIsEmpty() {
        Assertions.assertTrue(CollUtil.isEmpty(new ArrayList<>()));
        Assertions.assertTrue(CollUtil.isEmpty(new HashSet<>()));
    }
}
