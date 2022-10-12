package com.seconds.service;

import com.seconds.entity.WebInformation;

import java.sql.Date;

public interface WebInformationService {

    public void save(WebInformation webInformation);
    public String getRangeIpNum(Integer preDay);
    public String getRangePvNum(Integer preDay);
    public String getRangeUvNum(Integer preDay);
    public String getIpNum();
    public String getPvNum();
    public String getUvNum();
    public WebInformation getLastDayInformation();
    public void scheduledSave(Date date);
}
