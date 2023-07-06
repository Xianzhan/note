package xianzhan.frame.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import xianzhan.frame.json.jackson.introspector.MaskingIntrospector;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Jackson 工具类
 *
 * @author xianzhan
 * @since 2022-12-25
 */
public class JacksonUtil {

    private static final ObjectMapper MAPPER;

    static {
        MAPPER = init();
    }

    private static ObjectMapper init() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 打印格式化
        // objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        // 自引用则将该引用设为 null
        objectMapper.enable(SerializationFeature.WRITE_SELF_REFERENCES_AS_NULL);

        // 时间格式
        // 禁用 timestamp
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        // 自定义注解拦截
        var pair = AnnotationIntrospector.pair(objectMapper.getSerializationConfig()
                .getAnnotationIntrospector(), new MaskingIntrospector());
        objectMapper.setAnnotationIntrospector(pair);

        return objectMapper;
    }

    /**
     * 将对象转为 json 字符串
     *
     * @param obj 待转对象
     * @param <T> 对象类型
     * @return json 字符串
     */
    public static <T> String toJson(T obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException ignore) {
            // log?
        }
        return "";
    }

    /**
     * 将 json 字符串转为对象
     *
     * @param json  字符串
     * @param clazz 对象类型
     * @param <T>   对象类型
     * @return clazz 对象实例
     */
    public static <T> T toObj(String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException ignore) {
            // log?
        }
        return null;
    }

    /**
     * 将 json 字符串转为 list 集合
     *
     * @param json  字符串
     * @param clazz 集合元素 class
     * @param <T>   集合元素类型
     * @return list 集合
     */
    public static <T> List<T> toList(String json, Class<T> clazz) {
        CollectionType listType = MAPPER.getTypeFactory().constructCollectionType(List.class, clazz);
        try {
            return MAPPER.readValue(json, listType);
        } catch (JsonProcessingException ignore) {
            // log?
        }
        return List.of();
    }

    /**
     * 将 json 字符串转为 map
     *
     * @param json   字符串
     * @param kClass key class
     * @param vClass value class
     * @param <K>    key 类型
     * @param <V>    value 类型
     * @return map
     */
    public static <K, V> Map<K, V> toMap(String json, Class<K> kClass, Class<V> vClass) {
        MapType mapType = MAPPER.getTypeFactory().constructMapType(Map.class, kClass, vClass);
        try {
            return MAPPER.readValue(json, mapType);
        } catch (JsonProcessingException ignore) {
            // log?
        }
        return Map.of();
    }
}
