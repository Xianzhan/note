package xianzhan.note.json.jackson.i18n;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 国际化
 *
 * @author xianzhan
 * @since 2023-07-18
 */
@JacksonAnnotationsInside
@JsonSerialize(using = I18NSer.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface I18N {

}
