package com.kul.mapper;

import com.kul.pojo.Emp;

import java.util.List;

public interface DynamicSQLMapper {


    /**
     * ʹ�ö�̬SQL���ж�������ѯ
     */
    List<Emp> getEmpByCondition(Emp emp);
}
