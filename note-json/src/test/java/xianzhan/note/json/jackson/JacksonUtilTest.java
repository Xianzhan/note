package xianzhan.note.json.jackson;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import xianzhan.note.base.util.I18NUtil;
import xianzhan.note.json.jackson.pojo.IntToBinary;
import xianzhan.note.json.jackson.pojo.Response;
import xianzhan.note.json.jackson.pojo.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author xianzhan
 * @since 2022-12-25
 */
public class JacksonUtilTest {

    @Test
    public void testToJson() throws ParseException {
        // "null"
        String json = JacksonUtil.toJson(null);
        Assertions.assertEquals("null", json);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2022-12-25 18:48:00");

        // obj
        Student student = new Student();
        student.setName("xianzhan");
        student.setAge(10);
        student.setBirthday(date);

        json = JacksonUtil.toJson(student);
        Assertions.assertEquals("""
                {"name":"xianzhan","age":10,"birthday":"2022-12-25 18:48:00"}""", json);

        // list
        List<Student> list = new ArrayList<>();
        list.add(student);
        list.add(student);
        json = JacksonUtil.toJson(list);
        Assertions.assertEquals("""
                [{"name":"xianzhan","age":10,"birthday":"2022-12-25 18:48:00"},{"name":"xianzhan","age":10,"birthday":"2022-12-25 18:48:00"}]""", json);

        // map
        Map<String, Student> map = HashMap.newHashMap(1);
        map.put("xianzhan", student);
        json = JacksonUtil.toJson(map);
        Assertions.assertEquals("""
                {"xianzhan":{"name":"xianzhan","age":10,"birthday":"2022-12-25 18:48:00"}}""", json);
    }

    @Test
    public void testToObj() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String json = """
                {"name":"xianzhan","age":10,"birthday":"2022-12-25 04:29:25"}""";
        Student student = JacksonUtil.toObj(json, Student.class);
        Assertions.assertNotNull(student);
        Assertions.assertEquals("xianzhan", student.getName());
        Assertions.assertEquals(10, student.getAge());
        Assertions.assertEquals("2022-12-25 04:29:25", sdf.format(student.getBirthday()));

        System.out.println(student.getBirthday());
    }

    @Test
    public void testToList() {
        String json = """
                [{"name":"xianzhan","age":10,"birthday":"2022-12-25 18:48:00"},{"name":"xianzhan","age":10,"birthday":"2022-12-25 18:48:00"}]""";
        List<Student> students = JacksonUtil.toList(json, Student.class);
        Assertions.assertEquals(2, students.size());
    }

    @Test
    public void testToMap() {
        String json = """
                {"xianzhan":{"name":"xianzhan","age":10,"birthday":"2022-12-25 18:48:00"}}""";
        Map<String, Student> map = JacksonUtil.toMap(json, String.class, Student.class);
        Assertions.assertEquals(1, map.size());
        Assertions.assertTrue(map.containsKey("xianzhan"));
    }

    @Test
    public void testJsonSerializer() {
        IntToBinary tb = new IntToBinary();
        tb.setNum(256);
        tb.setNum2(256);
        tb.setBinString("100000000");
        Assertions.assertEquals("""
                {"num":"100000000","num2":"100000000","binString":"100***000"}""", JacksonUtil.toJson(tb));
    }

    @Test
    public void testI18N() {
        Response response = new Response();
        response.setCode(0);
        response.setMessage("成功");

        Assertions.assertEquals("""
                {"code":0,"message":"成功"}""", JacksonUtil.toJson(response));

        I18NUtil.setHolder(Locale.ENGLISH);
        Assertions.assertEquals("""
                {"code":0,"message":"success"}""", JacksonUtil.toJson(response));
    }
}
