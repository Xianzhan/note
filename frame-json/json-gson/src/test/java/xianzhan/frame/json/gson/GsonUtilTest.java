package xianzhan.frame.json.gson;

import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import xianzhan.frame.json.gson.pojo.Response;
import xianzhan.frame.json.gson.pojo.Student;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xianzhan
 * @since 2023-09-14
 */
public class GsonUtilTest {

    @Test
    public void testToJson() throws Exception {
        // "null"
        String json = GsonUtil.toJson(null);
        Assertions.assertEquals("null", json);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2022-12-25 18:48:00");

        // obj
        Student student = new Student();
        student.setName("xianzhan");
        student.setAge(10);
        student.setBirthday(date);

        json = GsonUtil.toJson(student);
        Assertions.assertEquals("""
                {"name":"xianzhan","age":10,"birthday":"2022-12-25 18:48:00"}""", json);
    }

    @Test
    public void testToObj() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String json = """
                {"name":"xianzhan","age":10,"birthday":"2022-12-25 04:29:25"}""";
        Student student = GsonUtil.toObj(json, Student.class);
        Assertions.assertNotNull(student);
        Assertions.assertEquals("xianzhan", student.getName());
        Assertions.assertEquals(10, student.getAge());
        Assertions.assertEquals("2022-12-25 04:29:25", sdf.format(student.getBirthday()));

        System.out.println(student.getBirthday());

        json = """
                {
                    "data": {
                        "name": "mike",
                        "age": 10,
                        "birthday": "2023-09-17 21:23:23"
                    }
                }""";
        Response<Student> response = GsonUtil.toObj(json, new TypeToken<>() {
        });
        System.out.println(response);

        Student data = response.getData();
        Assertions.assertEquals("mike", data.getName());
        Assertions.assertEquals(10, data.getAge());
    }
}
