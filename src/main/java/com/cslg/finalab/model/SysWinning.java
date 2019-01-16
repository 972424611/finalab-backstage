package com.cslg.finalab.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysWinning {
    private Integer id;

    private String name;

    private String awardName;

    private String awardLevel;

    private String awardImage;

    private Date time;

    private String members;

    private String profile;

}