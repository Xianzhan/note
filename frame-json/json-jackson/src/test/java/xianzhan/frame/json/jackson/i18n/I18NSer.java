package xianzhan.frame.json.jackson.i18n;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import xianzhan.frame.base.util.I18NUtil;

import java.io.IOException;

/**
 * 国际化映射实现
 *
 * @author xianzhan
 * @since 2023-07-18
 */
public class I18NSer extends StdSerializer<String> {
    protected I18NSer() {
        super(String.class);
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value == null) {
            return;
        }

        // 国际化映射
        // 方法1：使用 ResourceBundle
        String message = I18NUtil.getMessage(value);
        gen.writeString(message);

        // 方法2：使用数据库保存国际化数据
        // (1) 在数据库创建国际化表，表需要保存原语言与目标语言两个字段，以及对应的文本
        // (2) 从数据库查询后保存到分布式缓存(如 redis)，但要设置过期时间，避免不经常使用的占用内存，这样短时间内可以使用分布式缓存而不是数据库
        // (3) 最后保存最常用的数据到本地缓存，数据量大不了多少，例如 100 条，这样可以减少与分布式缓存的网络 I/O 时间
    }
}
