package xianzhan.note.json.jackson.introspector;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import xianzhan.note.json.jackson.annotation.Masking;
import xianzhan.note.json.jackson.ser.MaskingSer;

/**
 * @author xianzhan
 * @see Masking
 * @since 2023-07-06
 */
public class MaskingIntrospector extends NopAnnotationIntrospector {

    @Override
    public Object findSerializer(Annotated am) {
        Masking masking = am.getAnnotation(Masking.class);
        if (masking != null) {
            return new MaskingSer(masking.start(), masking.end());
        }
        return super.findSerializer(am);
    }
}
