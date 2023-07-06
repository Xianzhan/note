package xianzhan.frame.json.jackson.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import xianzhan.frame.json.jackson.ser.IntToBinarySer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自定义注解，必须有 @JacksonAnnotationsInside
 *
 * @author xianzhan
 * @since 2023-07-06
 */
@JacksonAnnotationsInside
@JsonSerialize(using = IntToBinarySer.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface IntToBin {

}
