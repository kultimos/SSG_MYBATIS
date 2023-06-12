package com.kul.pojo.mapper;

import com.kul.pojo.pojo.User;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface SelectMapper {

    //根据唯一id查询单条用户信息
    User selectById(Integer id);

    List<User> selectAll();

    Integer selectCountByName(String name);


    /**
     * MapKey的作用就是可以指定查询结果集中哪个字段作为map的key(通常我们都会选择主键)
     * 核心的点在于,如果不指定主键,那么返回多个map结果集的时候,会出现key重复的情况,就会报错
     * 而现在则完全不会,key是主键则唯一
     * 即如果不用MapKey注解需要声明一个List<Map>的结构体才能接收多个结果集
     * 使用MapKey注解可以直接声明一个Map结构体来接收多个结果集
     * 使用Map可以处理多表联查时某个不在主表的字段！！！！！！！
     * @param id
     * @return
     */
    @MapKey("id")
    Map selectMapById(Integer id);
}
