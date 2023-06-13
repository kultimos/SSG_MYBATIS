package com.kul.mapper;

import com.kul.pojo.Emp;

import java.util.List;

public interface EmpMapper {

    List<Emp> selectT();

    Emp selectDetailInfo(Integer eid);

    Emp getEmpAndDeptByStepOne(Integer eid);

    List<Emp> selectEmpByDid(Integer did);
}
