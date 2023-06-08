package xianzhan.frame.json.jackson.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 将整数转为二进制字符串
 *
 * @author xianzhan
 * @since 2023-06-08
 */
public class IntToBinarySer extends JsonSerializer<Integer> {

    @Override
    public void serialize(Integer value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value != null) {
            String binaryString = Integer.toBinaryString(value);
            gen.writeString(binaryString);
        }
    }
}
