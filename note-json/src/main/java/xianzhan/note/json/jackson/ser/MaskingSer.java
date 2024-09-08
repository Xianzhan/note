package xianzhan.note.json.jackson.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * @author xianzhan
 * @since 2023-07-06
 */
public class MaskingSer extends StdSerializer<String> {

    private final int start;
    private final int end;

    public MaskingSer(int start, int end) {
        super(String.class);
        this.start = start;
        this.end = end;
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value == null) {
            return;
        }
        if (start >= end) {
            gen.writeString(value);
            return;
        }
        if (value.length() >= start) {
            var builder = new StringBuilder();
            var length = value.length();
            builder.append(value, 0, start);
            if (length < end) {
                builder.append("*".repeat(Math.max(0, length - start)));
            } else {
                builder.append("*".repeat(Math.max(0, end - start)));
                builder.append(value, end, length);
            }
            gen.writeString(builder.toString());
        } else {
            gen.writeString(value);
        }
    }
}
