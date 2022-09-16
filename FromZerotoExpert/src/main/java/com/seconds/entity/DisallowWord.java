package com.seconds.entity;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DisallowWord {
    private Integer id;
    private String value;
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DisallowWords{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", type='" + type + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DisallowWord that = (DisallowWord) o;
        return Objects.equals(id, that.id) && Objects.equals(value, that.value) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, type);
    }
}
