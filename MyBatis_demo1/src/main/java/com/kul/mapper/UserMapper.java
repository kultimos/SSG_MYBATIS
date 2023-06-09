package com.kul.mapper;

import com.kul.pojo.User;

import java.util.List;

public interface UserMapper {
    int insertUser();

    void updateUser();

    int deleteUser();

    User selectById(int id);

    List<User> selectAll(String userName);
}
