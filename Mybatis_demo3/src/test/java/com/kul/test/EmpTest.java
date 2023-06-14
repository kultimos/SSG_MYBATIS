package com.kul.test;

import com.kul.mapper.DeptMapper;
import com.kul.mapper.DynamicSQLMapper;
import com.kul.mapper.EmpMapper;
import com.kul.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class EmpTest {

    private EmpMapper empMapper;

    private DeptMapper deptMapper;

    private DynamicSQLMapper dynamicSQLMapper;

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
        empMapper = sqlSession.getMapper(EmpMapper.class);
        deptMapper = sqlSession.getMapper(DeptMapper.class);
        dynamicSQLMapper = sqlSession.getMapper(DynamicSQLMapper.class);
    }

    @Test
    public void test() {
        empMapper.selectT().forEach(System.out::println);
    }

    @Test
    public void test2() {
        System.out.println(empMapper.selectDetailInfo(3));
    }

    @Test
    public void test3() {
        System.out.println(empMapper.getEmpAndDeptByStepOne(4).getEmpName());
    }

    @Test
    public void test4() {
        System.out.println(deptMapper.getDeptAndEmpInfo(1));
        System.out.println("------------------------------------");
        System.out.println(empMapper.selectEmpByDid(1));
    }

    @Test
    public void test5() {
        List<Emp> empList = dynamicSQLMapper.getEmpByConditionThree(Emp.builder().sex("��").email("1@qq.com").build());
        System.out.println(empList);
    }

    @Test
    public void test6() {
        List<Emp> empList = dynamicSQLMapper.getEmpByChoose(Emp.builder().sex("��").email("1@qq.com").build());
        System.out.println(empList);
    }
}
