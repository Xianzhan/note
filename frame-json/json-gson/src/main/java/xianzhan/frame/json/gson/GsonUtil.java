package xianzhan.frame.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
}
