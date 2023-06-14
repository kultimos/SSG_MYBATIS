package com.kul.mapper;

import com.kul.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DynamicSQLMapper {


    /**
     * 使用动态SQL进行多条件查询
     */
    List<Emp> getEmpByConditionOne(Emp emp);

    List<Emp> getEmpByConditionTwo(Emp emp);

    List<Emp> getEmpByConditionThree(Emp emp);

    List<Emp> getEmpByChoose(Emp emp);

    int deleteMoreByArray(@Param("eids") Integer[] eids);

    int deleteMoreByOr(@Param("eids") Integer[] eids);

    int insertMoreByArray(@Param("emps") List<Emp> emps);

    List<Emp> sqlEmp();
}
