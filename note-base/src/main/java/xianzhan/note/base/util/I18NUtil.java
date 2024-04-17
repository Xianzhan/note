package xianzhan.note.base.util;

//import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author xianzhan
 * @since 2023-07-18
 */
//@Slf4j
public class I18NUtil {

    private static final Logger log = LoggerFactory.getLogger(I18NUtil.class);

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
