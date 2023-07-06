package xianzhan.frame.json.jackson.annotation;

import xianzhan.frame.json.jackson.JacksonUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 将字符串 index 从 start 到 end(不包含) 的字符转为 `*`
 *
 * @author xianzhan
 * @see xianzhan.frame.json.jackson.ser.MaskingSer
 * @see xianzhan.frame.json.jackson.introspector.MaskingIntrospector
 * @see JacksonUtil#init()
 * @since 2023-07-06
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Masking {

    int start();

    int end();
}
