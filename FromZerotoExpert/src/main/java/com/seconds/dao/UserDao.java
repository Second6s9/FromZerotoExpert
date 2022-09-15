package com.seconds.dao;

import com.seconds.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
    @Select("select * from user where id = #{id}")
    public User selectById(Integer id);

    @Insert("insert into user(username, password, email, salt) values(#{username}, #{password}, #{email}, #{salt})")
    public void save(User user);

}
