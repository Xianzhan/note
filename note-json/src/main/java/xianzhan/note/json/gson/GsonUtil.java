package xianzhan.note.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * @author xianzhan
 * @since 2023-09-14
 */
public class GsonUtil {

    private static final Gson GSON = init();

    private static Gson init() {
        return new GsonBuilder()
//                .enableComplexMapKeySerialization()
                .serializeNulls()
                // hh 12 小时制, kk 24 小时制
                .setDateFormat("yyyy-MM-dd kk:mm:ss")
                .create();
    }

    public static <T> String toJson(T obj) {
        return GSON.toJson(obj);
    }

    public static <T> T toObj(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }

    /**
     * @param json      json 字符串
     * @param typeToken 泛型类型 token
     * @param <T>       泛型
     * @return 泛型实例
     */
    public static <T> T toObj(String json, TypeToken<T> typeToken) {
        return GSON.fromJson(json, typeToken.getType());
    }
}
