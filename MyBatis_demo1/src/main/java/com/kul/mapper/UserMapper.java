package com.kul.mapper;

import com.kul.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface UserMapper {
    int insertUser();

    void updateUser();

    int deleteUser();

    User selectById(User user);

    List<User> selectAll(String userName);

    User checkLogin(@Param("userName") String userName, @Param("password") String password);

    User checkLoginByMap(Map<String, Object> map);
}
