package com.kul.test;


import com.kul.mapper.SQLMapper;
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
import java.util.Arrays;

public class SelectMapperTest {

    private SelectMapper selectMapper;

    private SQLMapper sqlMapper;

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
        sqlMapper = sqlSession.getMapper(SQLMapper.class);
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

    @Test
    public void testSelectByLike() {
        sqlMapper.selectByLike("dm").forEach(System.out::println);
    }

    @Test
    public void testBatchDelete() {
        sqlMapper.batchDelete("admin");
    }

    @Test
    public void testDeleteIn() {
        sqlMapper.deleteIn(Arrays.asList(2,3,4,5,9,16,17));
    }

    @Test
    public void testSelectByTableName() {
        sqlMapper.selectByTableName("t_user").forEach(System.out::println);
    }

    @Test
    public void insertAutoKey() {
        //û������id,��mapper��������useGeneratedKeys="true" keyProperty="id",���Ի��Զ���������id��ֵ��user����
        sqlMapper.insertAutoKey(User.builder().age(55).userName("����ķ��").sex("��").email("mutomubo@163.com").password("123456").build());
    }
}
