package com.cslg.finalab.exception;

import com.cslg.finalab.enums.FileEnum;

/**
 * @author Twilight
 * @date 2019-02-06 14:49
 */
public class FileException extends CustomException {

    public FileException(Integer code, String message) {
        super(code, message);
    }

    public FileException(FileEnum fileEnum) {
        this(fileEnum.getCode(), fileEnum.getMessage());
    }
}
