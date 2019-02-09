package com.cslg.finalab.enums;

import lombok.Getter;

/**
 * @author Twilight
 * @date 19-1-16 下午4:29
 */
@Getter
public enum MemberEnum {

    // 如果前端传过来的参数为null，就抛出此异常
    INFORMATION_CANNOT_BE_EMPTY(200, "请填写相关信息"),

    // 成员已存在
    MEMBER_ALREADY_EXISTS(201, "学号重复"),

    // 成员不存在
    MEMBER_NOT_FOUND(202, "找不到该成员");

    private Integer code;

    private String message;

    MemberEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
