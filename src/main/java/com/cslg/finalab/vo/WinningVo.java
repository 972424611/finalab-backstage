package com.cslg.finalab.vo;

import lombok.Data;

import java.util.Date;

@Data
public class WinningVo {

    /** 项目名称 */
    private String name;

    private String awardName;

    private String awardLevel;

    private Date time;

    private String profile;

    private String awardImage;
}
