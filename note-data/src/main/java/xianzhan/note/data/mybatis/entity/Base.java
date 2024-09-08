package xianzhan.note.data.mybatis.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Base {

    private Long id;
    private String name;
    private Date createTime;
}
