package xianzhan.note.base.util;

import java.util.Collection;

/**
 * 集合框架工具类
 */
public class CollUtil {

    public static boolean isEmpty(Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }
}
