package com.kul.test;

import com.kul.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {


    @Test
    public void me() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //��ȡsqlSessionFactoryBuilder����
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //��ȡsqlSessionFactory����
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        //��ȡsql�ĻỰ����SqlSession,MyBatis�ṩ�Ĳ������ݿ�Ķ���,����true��ʾ�Զ��ύ����
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //��ȡUserMapper�Ĵ���ʵ�������
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //����Mapper�ӿڵķ�����ʵ������û���Ϣ�Ĺ���
        int result = userMapper.insertUser();
        System.out.println("�����" + result);
        //�ύ����
//        sqlSession.commit();
        //�رջỰ
        sqlSession.close();
    }
}

