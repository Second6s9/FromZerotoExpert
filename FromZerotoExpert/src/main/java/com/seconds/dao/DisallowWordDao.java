package com.seconds.dao;

import com.seconds.entity.DisallowWord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DisallowWordDao {
    @Select("select * from disallow_word")
    public List<DisallowWord> selectAll();
}
