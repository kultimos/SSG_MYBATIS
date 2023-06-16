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
//        //获取sqlSessionFactoryBuilder对象
//        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
//        //获取sqlSessionFactory对象
//        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
//        //获取sql的会话对象SqlSession,MyBatis提供的操作数据库的对象,参数true表示自动提交事务
//        sqlSession = sqlSessionFactory.openSession(true);
//        //获取UserMapper的代理实现类对象
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
        List<Emp> empList = dynamicSQLMapper.getEmpByConditionThree(Emp.builder().sex("男").email("1@qq.com").build());
        System.out.println(empList);
    }

    @Test
    public void test6() {
        List<Emp> empList = dynamicSQLMapper.getEmpByChoose(Emp.builder().sex("男").email("1@qq.com").build());
        System.out.println(empList);
    }

    @Test
    public void test7() {
        Integer[] eids = {7,8};
        System.out.println(dynamicSQLMapper.deleteMoreByOr(eids));
    }

    @Test
    public void test8() {
        Emp emp1 = Emp.builder().eid(10).empName("字母").sex("密尔沃基").age(29).email("希腊").build();
        Emp emp2 = Emp.builder().eid(11).empName("邓肯").sex("安东尼奥").age(40).email("美国").build();
        Emp emp3 = Emp.builder().eid(12).empName("德克").sex("达拉斯加").age(42).email("德国").build();
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
     * 一个面试题
     * 使一级缓存失效的情况:
     * 1.不同的sqlSession对应不同的一级缓存
     * 2.同一个sqlSession但是查询条件不同
     * 3.同一个sqlSession但是两次查询之间执行了增删改操作
     * 4.同一个sqlSession但是手动清除了一级缓存
     */
    @Test
    public void test10() throws InterruptedException {
        //默认开启一级缓存,一级缓存是sqlSession级别的缓存,一级缓存是一直开启的,不能关闭
        //这里就可以体现mybatis的一级缓存了—————sqlSession级别的缓存
        //因为是两条完全一样的sql,所以是同一个sqlSession,所以第二次查询的时候就不会再去数据库查询了,而是直接从缓存中取
        //这里我们睡眠10s并且再过程中修改了数据库的数据,但是第二次查询的时候还是会从缓存中取;所以两次查询结果一样
        System.out.println(cacheMapper.getEmpById(23));
        Thread.sleep(10000);
        new Thread(() -> {
            System.out.println(cacheMapper.getEmpById(23));
        }).start();
    }

    /**
     * 不同的sqlSession对应不同的一级缓存
     * 所以两次查询的结果是不一样的
     */
    @Test
    public void test11() throws InterruptedException, IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        //获取sql的会话对象SqlSession,MyBatis提供的操作数据库的对象,参数true表示自动提交事务
        SqlSession sqlSessionTest = sqlSessionFactory.openSession(true);
        //获取UserMapper的代理实现类对象

        System.out.println(cacheMapper.getEmpById(23));
        Thread.sleep(10000);
        CacheMapper cacheMapper1 = sqlSessionTest.getMapper(CacheMapper.class);
        System.out.println(cacheMapper1.getEmpById(23));
    }

    /**
     * 手动清除一级缓存
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
     * 两次查询之间执行了增删改操作,使一级缓存失效
     * @throws InterruptedException
     */
    @Test
    public void test13() throws InterruptedException {
        System.out.println(cacheMapper.getEmpById(23));
        System.out.println(cacheMapper.insertEmp(Emp.builder().empName(UUID.randomUUID().toString()).sex("女").email("sa")
                .age(58).build()));
        Thread.sleep(10000);
        new Thread(() -> {
            System.out.println(cacheMapper.getEmpById(23));
        }).start();
    }


    /**
     * 二级缓存
     * 在我们没有关闭/提交sqlSession的情况下,查询到的数据会被保存到一级缓存中
     * 当我们关闭/提交sqlSession的时候,一级缓存中的数据会被保存到二级缓存中
     *
     * 二级缓存开启条件：
     * 1.在核心配置文件中,设置全局配置参数<setting name="cacheEnabled" value="true"/> , 默认为true,不需要设置
     * 2.在映射文件mapper.xml中,配置<cache/>标签
     * 3.二级缓存必须在SqlSession关闭或提交之后才会生效
     * 4.查询的数据所转换的实体类类型必须实现序列化接口
     *
     * 二级缓存失效的情况：
     * 两次查询之间执行了增删改操作,会使一级缓存、二级缓存都失效
     *
     * mybatis缓存查询的顺序：
     * 1.先看二级缓存中有没有数据,如果有就直接返回
     * 2.如果二级缓存中没有数据,就去一级缓存中查看有没有数据,如果有就直接返回
     * 3.如果一级缓存中没有数据,就去数据库中查询,并且将查询到的数据保存到一级缓存中
     * 注:sqlSession关闭后,将一级缓存中的数据保存到二级缓存中
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
