package com.cslg.finalab.exception;

import com.cslg.finalab.enums.MemberEnum;

/**
 * @author Twilight
 * @date 19-1-16 下午4:21
 */
public class MemberException extends CustomException {


    public MemberException(Integer code, String message) {
        super(code, message);
    }

    public MemberException(MemberEnum memberEnum) {
        this(memberEnum.getCode(), memberEnum.getMessage());
    }
}
