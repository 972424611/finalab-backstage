package com.cslg.finalab.enums;

import lombok.Getter;

@Getter
public enum ProjectEnum {

    // 项目已经存在
    PROJECT_ALREADY_EXISTS(300, "项目已经存在"),

    // 技术负责人和策划负责人不能为同一个人
    PLANNER_ARTISAN_NOT_THE_SAME_PEOPLE(301, "技术负责人和策划负责人不能为同一个人"),

    // 找不到该项目
    PROJECT_NOT_FOUND(302, "找不到该项目");

    private Integer code;

    private String message;

    ProjectEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
