package com.kul.mapper;

import com.kul.pojo.Emp;

import java.util.List;

public interface DynamicSQLMapper {


    /**
     * ʹ�ö�̬SQL���ж�������ѯ
     */
    List<Emp> getEmpByConditionOne(Emp emp);

    List<Emp> getEmpByConditionTwo(Emp emp);

    List<Emp> getEmpByConditionThree(Emp emp);



    List<Emp> getEmpByChoose(Emp emp);
}
