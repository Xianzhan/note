package xianzhan.frame.json.jackson.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import xianzhan.frame.json.jackson.annotation.IntToBin;
import xianzhan.frame.json.jackson.annotation.Masking;
import xianzhan.frame.json.jackson.ser.IntToBinarySer;

/**
 * @author xianzhan
 * @since 2023-06-08
 */
@Data
public class IntToBinary {

    @JsonSerialize(using = IntToBinarySer.class)
    private Integer num;

    @IntToBin
    private Integer num2;

    @Masking(start = 3, end = 6)
    private String binString;
}
