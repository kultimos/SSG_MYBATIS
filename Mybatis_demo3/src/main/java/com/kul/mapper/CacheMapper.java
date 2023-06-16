package com.kul.mapper;

import com.kul.pojo.Emp;
import org.apache.ibatis.annotations.Param;

public interface CacheMapper {

    Emp getEmpById(@Param("eid") Integer eid);


    Integer insertEmp(Emp emp);
}
