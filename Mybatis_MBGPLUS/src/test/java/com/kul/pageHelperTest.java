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
        //获取sqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        //获取sql的会话对象SqlSession,MyBatis提供的操作数据库的对象,参数true表示自动提交事务
        sqlSession = sqlSessionFactory.openSession(true);
        //获取UserMapper的代理实现类对象
        empMapper = sqlSession.getMapper(EmpMapper.class);
    }

    /**
     * 最简单的分页能力实现
     */
    @Test
    public void test () {
        PageHelper.startPage(2, 3);
        empMapper.selectByExample(null).forEach(emp -> System.out.println(emp));
    }
}
