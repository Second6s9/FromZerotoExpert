package com.seconds.entity;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Objects;

@Component
public class WebInformation {
    private Integer id;
    private Date date;
    private Integer ip;
    private Integer pv;
    private Integer uv;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getIp() {
        return ip;
    }

    public void setIp(Integer ip) {
        this.ip = ip;
    }

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }

    @Override
    public String toString() {
        return "WebInformation{" +
                "id=" + id +
                ", date=" + date +
                ", ip=" + ip +
                ", pv=" + pv +
                ", uv=" + uv +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebInformation that = (WebInformation) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(ip, that.ip) && Objects.equals(pv, that.pv) && Objects.equals(uv, that.uv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, ip, pv, uv);
    }
}
