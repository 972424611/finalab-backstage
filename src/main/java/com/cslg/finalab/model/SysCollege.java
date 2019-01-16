package com.cslg.finalab.model;

import lombok.Data;

@Data
public class SysCollege {
    private Integer id;

    private String collegeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}