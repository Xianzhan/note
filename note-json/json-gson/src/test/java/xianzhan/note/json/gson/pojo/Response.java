package xianzhan.note.json.gson.pojo;



/**
 * @author xianzhan
 * @since 2023-09-17
 */
//@Data
public class Response<T> {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
