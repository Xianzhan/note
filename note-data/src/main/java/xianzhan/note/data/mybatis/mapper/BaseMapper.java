package xianzhan.note.data.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import xianzhan.note.data.mybatis.entity.Base;

public interface BaseMapper {

    Base selectBase(@Param("id") Long id);
}
