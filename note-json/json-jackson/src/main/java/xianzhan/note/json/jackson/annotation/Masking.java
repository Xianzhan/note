package xianzhan.note.json.jackson.annotation;

import xianzhan.note.json.jackson.JacksonUtil;
import xianzhan.note.json.jackson.introspector.MaskingIntrospector;
import xianzhan.note.json.jackson.ser.MaskingSer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 将字符串 index 从 start 到 end(不包含) 的字符转为 `*`
 *
 * @author xianzhan
 * @see MaskingSer
 * @see MaskingIntrospector
 * @see JacksonUtil#init()
 * @since 2023-07-06
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Masking {

    int start();

    int end();
}
