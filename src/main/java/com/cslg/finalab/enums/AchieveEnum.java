package com.cslg.finalab.enums;

import lombok.Getter;

/**
 * @author Twilight
 * @date 19-2-2 下午9:47
 */
@Getter
public enum AchieveEnum {

    // 成员已存在
    ACHIEVE_ALREADY_EXISTS(1, "已有该成员的去向");

    private Integer code;

    private String message;

    AchieveEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
