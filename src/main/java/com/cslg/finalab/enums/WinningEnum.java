package com.cslg.finalab.enums;

import lombok.Getter;

/**
 * @author Twilight
 * @date 2019-02-11 10:00
 */
@Getter
public enum  WinningEnum {

    // 该奖项目不存在
    WINNING_NOT_FOUND(501, "该奖项目不存在");

    private Integer code;

    private String message;

    WinningEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
