package com.seconds.service.impl;

import com.seconds.dao.WebInformationDao;
import com.seconds.entity.WebInformation;
import com.seconds.service.WebInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class WebInformationServiceImpl implements WebInformationService {
    @Autowired
    private WebInformationDao webInformationDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 向数据库插入当天的网页访问信息
     * @param webInformation
     */
    @Override
    public void save(WebInformation webInformation) {
        webInformationDao.save(webInformation);
    }

    @Override
    public String getRangeIpNum(Integer preDay) {
        Date date = new Date(System.currentTimeMillis());
        Integer end = webInformationDao.getWebInformationByDate(date).getId();
        int start = end - preDay;
        Integer res = 0;
        try {
            res = webInformationDao.getRangeIpNum(start, end);
            if(res == null) res = 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return res + "";
    }

    @Override
    public String getRangePvNum(Integer preDay) {
        Date date = new Date(System.currentTimeMillis());
        Integer end = webInformationDao.getWebInformationByDate(date).getId();
        int start = end - preDay;
        Integer res = 0;
        try {
            res = webInformationDao.getRangePvNum(start, end);
            if(res == null) res = 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return res + "";
    }

    @Override
    public String getRangeUvNum(Integer preDay) {
        Date date = new Date(System.currentTimeMillis());
        Integer end = webInformationDao.getWebInformationByDate(date).getId();
        int start = end - preDay;
        Integer res = 0;
        try {
            res = webInformationDao.getRangeUvNum(start, end);
            if(res == null) res = 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return res + "";
    }

    @Override
    public String getIpNum() {
        try {
            Date date = new Date(System.currentTimeMillis());
            String key = "web:ip:" + date.toString();
            Long res = stringRedisTemplate.opsForHyperLogLog().size(key);
            if(res == null) res = 0L;
            return res + "";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "0";
    }

    @Override
    public String getPvNum() {
        try {
            Date date = new Date(System.currentTimeMillis());
            String key = "web:pv:" + date.toString();
            String res = stringRedisTemplate.opsForValue().get(key);
            if(res == null || "".equals(res)) res = "0";
            return res;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "0";
    }

    @Override
    public String getUvNum() {
        try {
            Date date = new Date(System.currentTimeMillis());
            String key = "web:uv:" + date.toString();
            Long res = stringRedisTemplate.opsForHyperLogLog().size(key);
            if(res == null) res = 0L;
            return res + "";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "0";
    }

    @Override
    public WebInformation getLastDayInformation() {
        Date date = new Date(System.currentTimeMillis());
        Integer id = webInformationDao.getWebInformationByDate(date).getId();
        WebInformation webInformation = webInformationDao.getWebInformationById(id - 1);
        return webInformation;
    }

    @Override
    public void scheduledSave(Date date) {
        String ip_key = "web:ip:" + date.toString();
        String pv_key = "web:pv:" + date.toString();
        String uv_key = "web:uv:" + date.toString();
        String pv_res = stringRedisTemplate.opsForValue().get(pv_key);
        Long ip_res = stringRedisTemplate.opsForHyperLogLog().size(ip_key);
        Long uv_res = stringRedisTemplate.opsForHyperLogLog().size(uv_key);

        WebInformation webInformation = new WebInformation();
        webInformation.setDate(date);
        if(ip_res == null) ip_res = 0L;
        Integer ip = Integer.parseInt(ip_res.toString());
        if(pv_res == null) pv_res = "0";
        Integer pv = Integer.parseInt(pv_res);
        Integer uv = Integer.parseInt(uv_res.toString());
        if(uv_res == null) uv_res = 0L;

        webInformation.setPv(pv);

        webInformation.setUv(uv);

        webInformation.setIp(ip);

        webInformationDao.save(webInformation);
    }


}
