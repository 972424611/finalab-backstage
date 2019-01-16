package com.cslg.finalab.model;

import lombok.Data;

@Data
public class SysAchieve {

    private Integer id;

    private String stuId;

    private String whereabouts;

    private String catchphrase;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }
}