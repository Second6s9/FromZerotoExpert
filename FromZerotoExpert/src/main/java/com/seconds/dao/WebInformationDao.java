package com.seconds.dao;

import com.seconds.entity.User;
import com.seconds.entity.WebInformation;
import org.apache.ibatis.annotations.*;

import java.sql.Date;

@Mapper
public interface WebInformationDao {

    @Insert("insert into web_information(date, ip, pv, uv) values(#{date}, #{ip}, #{pv}, #{uv})")
    public void save(WebInformation webInformation);

    @Select("select count(id) from web_information")
    public Integer getTotalNums();

    @Select("select * from web_information where date = #{date} limit 1")
    public WebInformation getWebInformationByDate(Date date);

    @Select("select * from web_information where id = #{id}")
    public WebInformation getWebInformationById(Integer id);

    @Delete("delete from web_information where id = #{id}")
    public void deleteById(Integer id);

    @Select("select min(id) from web_information")
    public Integer getMinId();

    @Select("select max(id) from web_information")
    public Integer getMaxId();

    @Select("select SUM(ip) from web_information WHERE id >= #{start} AND id < #{end}")
    public Integer getRangeIpNum(@Param(value = "start")Integer start, @Param(value = "end")Integer end);

    @Select("select SUM(pv) from web_information WHERE id >= #{start} AND id < #{end}")
    public Integer getRangePvNum(@Param(value = "start")Integer start, @Param(value = "end")Integer end);

    @Select("select SUM(uv) from web_information WHERE id >= #{start} AND id < #{end}")
    public Integer getRangeUvNum(@Param(value = "start")Integer start, @Param(value = "end")Integer end);

}
