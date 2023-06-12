package com.kul.test;


import com.kul.mapper.SelectMapper;
import com.kul.mapper.UserMapper;
import com.kul.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class SelectMapperTest {

    private SelectMapper selectMapper;

    @Before
    public void init() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //��ȡsqlSessionFactoryBuilder����
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //��ȡsqlSessionFactory����
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        //��ȡsql�ĻỰ����SqlSession,MyBatis�ṩ�Ĳ������ݿ�Ķ���,����true��ʾ�Զ��ύ����
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //��ȡUserMapper�Ĵ���ʵ�������
        selectMapper = sqlSession.getMapper(SelectMapper.class);
    }



    @Test
    public void testSelectById() throws IOException {
        User user = selectMapper.selectById(1);
        System.out.println(user);
    }

    @Test
    public void testSelectAll() {
        selectMapper.selectAll().forEach(System.out::println);
    }

    @Test
    public void testSelectCountByName() {
        Integer count = selectMapper.selectCountByName("admin");
        System.out.println(count);
    }

    @Test
    public void testSelectMapById() {
        System.out.println(selectMapper.selectMapById(9));
    }
}
