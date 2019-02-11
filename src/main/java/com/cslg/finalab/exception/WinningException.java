package com.cslg.finalab.exception;

import com.cslg.finalab.enums.WinningEnum;
import com.cslg.finalab.param.WinningParam;

/**
 * @author Twilight
 * @date 2019-02-11 10:00
 */
public class WinningException extends CustomException {

    public WinningException(Integer code, String message) {
        super(code, message);
    }

    public WinningException(WinningEnum winningEnum) {
        this(winningEnum.getCode(), winningEnum.getMessage());
    }
}
