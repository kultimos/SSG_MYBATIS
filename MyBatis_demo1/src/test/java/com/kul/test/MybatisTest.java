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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

        List<User> useList = userMapper.selectAll("kultimos");
        System.out.println(useList);
//        User kultimos = userMapper.checkLogin("kultimos", "123456");
//        Map<String, Object> map = new HashMap<>();
//        map.put("userName", "kultimos");
//        map.put("password", "123456");
//        User user = userMapper.checkLoginByMap(map);
////        System.out.println(user);
//        User user = userMapper.selectById(User.builder().id(1).build());
//        System.out.println(user);
        //�ύ����
//        sqlSession.commit();
        //�رջỰ
        sqlSession.close();
    }
}

