package xianzhan.note.data.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import xianzhan.note.data.mybatis.entity.Base;
import xianzhan.note.data.mybatis.mapper.BaseMapper;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisMain {
    public static void main(String[] args) throws IOException {

        String configResource = "mybatis/mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(configResource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        try (SqlSession sqlSession = factory.openSession()) {
            BaseMapper baseMapper = sqlSession.getMapper(BaseMapper.class);
            Base base = baseMapper.selectBase(1L);
            System.out.println(base);
        }
    }
}
