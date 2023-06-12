package com.kul.mapper;

import com.kul.pojo.User;

import java.util.List;

public interface SQLMapper {

    List<User> selectByLike(String userName);

    void batchDelete(String userName);

    void deleteIn(List<Integer> userNameList);

    List<User> selectByTableName(String tableName);
}
