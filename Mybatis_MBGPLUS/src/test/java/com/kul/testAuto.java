package com.kul;

import com.kul.mapper.DeptMapper;
import com.kul.mapper.EmpMapper;
import com.kul.pojo.Emp;
import com.kul.pojo.EmpExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class testAuto {
    private EmpMapper empMapper;

    private DeptMapper deptMapper;



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

    @Test
    public void test(){
//       empMapper.selectByExample(null).forEach(emp -> System.out.println(emp));
        EmpExample example = new EmpExample();
        example.createCriteria().andEmpNameEqualTo("ʲô");
        empMapper.selectByExample(example).forEach(emp -> System.out.println(emp));
    }

}
