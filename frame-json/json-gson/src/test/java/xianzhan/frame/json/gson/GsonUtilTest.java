package xianzhan.frame.json.gson;

import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import xianzhan.frame.json.gson.pojo.Response;
import xianzhan.frame.json.gson.pojo.Student;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

        json = """
                {
                    "1":1,
                    "2":2,
                    "3":3
                }""";
        Map<String, Integer> map = GsonUtil.toObj(json, new TypeToken<>() {
        });
        Assertions.assertEquals(1, map.get("1"));
        Assertions.assertEquals(2, map.get("2"));
        Assertions.assertEquals(3, map.get("3"));
        Assertions.assertNull(map.get("4"));

        json = """
                [
                    {
                        "name":"name1",
                        "age":1
                    },
                    {
                        "name":"name2",
                        "age":2
                    },
                    {
                        "name":"name3",
                        "age":3
                    }
                ]""";
        List<Student> list = GsonUtil.toObj(json, new TypeToken<>() {
        });
        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals("name1", list.get(0).getName());
        Assertions.assertEquals(2, list.get(1).getAge());
        Assertions.assertEquals("name3", list.get(2).getName());
        Assertions.assertEquals(3, list.get(2).getAge());
        Assertions.assertNull(list.get(2).getBirthday());
    }
}
