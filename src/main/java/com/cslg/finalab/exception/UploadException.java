package com.cslg.finalab.exception;

import com.cslg.finalab.enums.UploadEnum;

/**
 * @author Twilight
 * @date 2019-02-06 14:49
 */
public class UploadException extends CustomException {

    public UploadException(Integer code, String message) {
        super(code, message);
    }

    public UploadException(UploadEnum uploadEnum) {
        this(uploadEnum.getCode(), uploadEnum.getMessage());
    }
}
