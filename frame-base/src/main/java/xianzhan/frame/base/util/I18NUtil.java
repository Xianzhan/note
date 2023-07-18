package xianzhan.frame.base.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author xianzhan
 * @since 2023-07-18
 */
@Slf4j
public class I18NUtil {

    private static final ThreadLocal<Locale> HOLDER = ThreadLocal.withInitial(() -> Locale.CHINESE);

    public static void setHolder(Locale locale) {
        HOLDER.set(locale);
    }

    public static void clearHolder() {
        HOLDER.remove();
    }

    public static String getMessage(String key) {
        if (key == null) {
            return null;
        }

        try {
            var bundle = ResourceBundle.getBundle("message", HOLDER.get());
            return bundle.getString(key);
        } catch (MissingResourceException ignore) {
            log.error("I18N - getMessage: 未配置国际化. key: {}", key);
        }
        return key;
    }
}
