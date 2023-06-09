import com.kul.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestInsert {


    @Test
    public void me() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //��ȡsqlSessionFactoryBuilder����
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //��ȡsqlSessionFactory����
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        //��ȡsql�ĻỰ����SqlSession,MyBatis�ṩ�Ĳ������ݿ�Ķ���
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //��ȡUserMapper�Ĵ���ʵ�������
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //����Mapper�ӿڵķ�����ʵ������û���Ϣ�Ĺ���
        int result = userMapper.insertUser();
        System.out.println("�����" + result);
        //�ύ����
        sqlSession.commit();
        //�رջỰ
        sqlSession.close();
    }
}

