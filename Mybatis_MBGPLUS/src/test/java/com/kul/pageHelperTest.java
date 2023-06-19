package com.kul;

import com.github.pagehelper.PageHelper;
import com.kul.mapper.DeptMapper;
import com.kul.mapper.EmpMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class pageHelperTest {

    private EmpMapper empMapper;



    private SqlSession sqlSession;

    @Before
    public void init() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //��ȡsqlSessionFactoryBuilder����
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //��ȡsqlSessionFactory����
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        //��ȡsql�ĻỰ����SqlSession,MyBatis�ṩ�Ĳ������ݿ�Ķ���,����true��ʾ�Զ��ύ����
        sqlSession = sqlSessionFactory.openSession(true);
        //��ȡUserMapper�Ĵ���ʵ�������
        empMapper = sqlSession.getMapper(EmpMapper.class);
    }

    /**
     * ��򵥵ķ�ҳ����ʵ��
     */
    @Test
    public void test () {
        PageHelper.startPage(2, 3);
        empMapper.selectByExample(null).forEach(emp -> System.out.println(emp));
    }
}
