package xianzhan.note.json.gson.pojo;


import lombok.Data;

/**
 * @author xianzhan
 * @since 2023-09-17
 */
@Data
public class Response<T> {

    private T data;
}
