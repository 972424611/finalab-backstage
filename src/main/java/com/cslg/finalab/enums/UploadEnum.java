package com.cslg.finalab.enums;

import lombok.Getter;

/**
 * @author Twilight
 * @date 2019-02-06 14:50
 */
@Getter
public enum  UploadEnum {

    IMAGE_TOO_LARGE(400, "图片过大, 请使用小于5MB的图片上传"),

    IMAGE_FORMAT_ERROR(401, "请使用格式为.jpg或.png的图片"),

    CREATE_FILE_FAILED(402, "创建文件失败"),

    SERVER_BUSY(403, "服务器繁忙");

    private Integer code;

    private String message;

    UploadEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
