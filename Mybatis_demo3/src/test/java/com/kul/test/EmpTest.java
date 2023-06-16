package com.kul.test;

import com.kul.mapper.CacheMapper;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EmpTest {

    private EmpMapper empMapper;

    private DeptMapper deptMapper;

    private DynamicSQLMapper dynamicSQLMapper;

    private CacheMapper cacheMapper;

    private SqlSession sqlSession;

//    @Before
//    public void init() throws IOException {
//        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
//        //��ȡsqlSessionFactoryBuilder����
//        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
//        //��ȡsqlSessionFactory����
//        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
//        //��ȡsql�ĻỰ����SqlSession,MyBatis�ṩ�Ĳ������ݿ�Ķ���,����true��ʾ�Զ��ύ����
//        sqlSession = sqlSessionFactory.openSession(true);
//        //��ȡUserMapper�Ĵ���ʵ�������
//        empMapper = sqlSession.getMapper(EmpMapper.class);
//        deptMapper = sqlSession.getMapper(DeptMapper.class);
//        dynamicSQLMapper = sqlSession.getMapper(DynamicSQLMapper.class);
//        cacheMapper = sqlSession.getMapper(CacheMapper.class);
//    }

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

    @Test
    public void test7() {
        Integer[] eids = {7,8};
        System.out.println(dynamicSQLMapper.deleteMoreByOr(eids));
    }

    @Test
    public void test8() {
        Emp emp1 = Emp.builder().eid(10).empName("��ĸ").sex("�ܶ��ֻ�").age(29).email("ϣ��").build();
        Emp emp2 = Emp.builder().eid(11).empName("�˿�").sex("�������").age(40).email("����").build();
        Emp emp3 = Emp.builder().eid(12).empName("�¿�").sex("����˹��").age(42).email("�¹�").build();
        List<Emp> list = new ArrayList<>();
        list.add(emp1);
        list.add(emp2);
        list.add(emp3);
        int a = dynamicSQLMapper.insertMoreByArray(list);
        System.out.println(a);
    }

    @Test
    public void test9(){
        dynamicSQLMapper.sqlEmp().forEach(x -> System.out.println(x));
    }

    /**
     * һ��������
     * ʹһ������ʧЧ�����:
     * 1.��ͬ��sqlSession��Ӧ��ͬ��һ������
     * 2.ͬһ��sqlSession���ǲ�ѯ������ͬ
     * 3.ͬһ��sqlSession�������β�ѯ֮��ִ������ɾ�Ĳ���
     * 4.ͬһ��sqlSession�����ֶ������һ������
     */
    @Test
    public void test10() throws InterruptedException {
        //Ĭ�Ͽ���һ������,һ��������sqlSession����Ļ���,һ��������һֱ������,���ܹر�
        //����Ϳ�������mybatis��һ�������ˡ���������sqlSession����Ļ���
        //��Ϊ��������ȫһ����sql,������ͬһ��sqlSession,���Եڶ��β�ѯ��ʱ��Ͳ�����ȥ���ݿ��ѯ��,����ֱ�Ӵӻ�����ȡ
        //��������˯��10s�����ٹ������޸������ݿ������,���ǵڶ��β�ѯ��ʱ���ǻ�ӻ�����ȡ;�������β�ѯ���һ��
        System.out.println(cacheMapper.getEmpById(23));
        Thread.sleep(10000);
        new Thread(() -> {
            System.out.println(cacheMapper.getEmpById(23));
        }).start();
    }

    /**
     * ��ͬ��sqlSession��Ӧ��ͬ��һ������
     * �������β�ѯ�Ľ���ǲ�һ����
     */
    @Test
    public void test11() throws InterruptedException, IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //��ȡsqlSessionFactory����
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        //��ȡsql�ĻỰ����SqlSession,MyBatis�ṩ�Ĳ������ݿ�Ķ���,����true��ʾ�Զ��ύ����
        SqlSession sqlSessionTest = sqlSessionFactory.openSession(true);
        //��ȡUserMapper�Ĵ���ʵ�������

        System.out.println(cacheMapper.getEmpById(23));
        Thread.sleep(10000);
        CacheMapper cacheMapper1 = sqlSessionTest.getMapper(CacheMapper.class);
        System.out.println(cacheMapper1.getEmpById(23));
    }

    /**
     * �ֶ����һ������
     * @throws InterruptedException
     */
    @Test
    public void test12() throws InterruptedException {
        System.out.println(cacheMapper.getEmpById(23));
        Thread.sleep(10000);
        sqlSession.clearCache();
        System.out.println(cacheMapper.getEmpById(23));
    }


    /**
     * ���β�ѯ֮��ִ������ɾ�Ĳ���,ʹһ������ʧЧ
     * @throws InterruptedException
     */
    @Test
    public void test13() throws InterruptedException {
        System.out.println(cacheMapper.getEmpById(23));
        System.out.println(cacheMapper.insertEmp(Emp.builder().empName(UUID.randomUUID().toString()).sex("Ů").email("sa")
                .age(58).build()));
        Thread.sleep(10000);
        new Thread(() -> {
            System.out.println(cacheMapper.getEmpById(23));
        }).start();
    }


    /**
     * ��������
     * ������û�йر�/�ύsqlSession�������,��ѯ�������ݻᱻ���浽һ��������
     * �����ǹر�/�ύsqlSession��ʱ��,һ�������е����ݻᱻ���浽����������
     *
     * �������濪��������
     * 1.�ں��������ļ���,����ȫ�����ò���<setting name="cacheEnabled" value="true"/> , Ĭ��Ϊtrue,����Ҫ����
     * 2.��ӳ���ļ�mapper.xml��,����<cache/>��ǩ
     * 3.�������������SqlSession�رջ��ύ֮��Ż���Ч
     * 4.��ѯ��������ת����ʵ�������ͱ���ʵ�����л��ӿ�
     *
     * ��������ʧЧ�������
     * ���β�ѯ֮��ִ������ɾ�Ĳ���,��ʹһ�����桢�������涼ʧЧ
     *
     * mybatis�����ѯ��˳��
     * 1.�ȿ�������������û������,����о�ֱ�ӷ���
     * 2.�������������û������,��ȥһ�������в鿴��û������,����о�ֱ�ӷ���
     * 3.���һ��������û������,��ȥ���ݿ��в�ѯ,���ҽ���ѯ�������ݱ��浽һ��������
     * ע:sqlSession�رպ�,��һ�������е����ݱ��浽����������
     */
    @Test
    public void testTwoCache()   {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();


            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
            SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
            CacheMapper cacheMapper1 = sqlSession1.getMapper(CacheMapper.class);
            System.out.println(cacheMapper1.getEmpById(23));
            sqlSession1.close();
            Thread.sleep(10000);
            SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
            CacheMapper cacheMapper2 = sqlSession2.getMapper(CacheMapper.class);
            System.out.println(cacheMapper2.getEmpById(23));
            sqlSession2.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
