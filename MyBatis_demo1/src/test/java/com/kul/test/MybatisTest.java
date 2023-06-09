package com.kul.test;

import com.kul.mapper.UserMapper;
import com.kul.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
//        int result = userMapper.insertUser();
//        System.out.println("�����" + result);
//        userMapper.updateUser();
        User user = userMapper.selectById(1);
        List<User> useList = userMapper.selectAll("admin");
        System.out.println(useList);
        //�ύ����
//        sqlSession.commit();
        //�رջỰ
        sqlSession.close();
    }
}

