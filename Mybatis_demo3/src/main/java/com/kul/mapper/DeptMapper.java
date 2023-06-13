package com.kul.mapper;

import com.kul.pojo.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapper {

    Dept getEmpAndDeptByStepTwo(Integer did);

    Dept getDeptAndEmpInfo(@Param("did") Integer did);
}
