package com.cslg.finalab.exception;

import com.cslg.finalab.enums.AchieveEnum;

/**
 * @author Twilight
 * @date 19-2-2 下午9:46
 */
public class AchieveException extends CustomException {
    public AchieveException(Integer code, String message) {
        super(code, message);
    }

    public AchieveException(AchieveEnum achieveEnum) {
        this(achieveEnum.getCode(), achieveEnum.getMessage());
    }
}
