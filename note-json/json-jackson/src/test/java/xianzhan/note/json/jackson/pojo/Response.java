package xianzhan.note.json.jackson.pojo;

import lombok.Data;
import xianzhan.note.json.jackson.i18n.I18N;

/**
 * @author xianzhan
 * @since 2023-07-18
 */
@Data
public class Response {

    private Integer code;
    @I18N
    private String  message;
}
