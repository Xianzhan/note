package xianzhan.frame.json.gson.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author xianzhan
 * @since 2022-12-25
 */
@Data
public class Student {

    private String name;
    private int    age;
    private Date   birthday;
}
