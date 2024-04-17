package xianzhan.note.base.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Locale;

/**
 * @author xianzhan
 * @since 2023-07-18
 */
public class I18NUtilTest {

    @Test
    public void testGetMessage() {
        Assertions.assertEquals("成功", I18NUtil.getMessage("成功"));

        I18NUtil.setHolder(Locale.ENGLISH);

        Assertions.assertEquals("success", I18NUtil.getMessage("成功"));
        Assertions.assertEquals("未知", I18NUtil.getMessage("未知"));

        I18NUtil.clearHolder();
    }
}
