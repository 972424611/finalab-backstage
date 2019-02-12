package com.cslg.finalab.enums;

import lombok.Getter;

/**
 * @author Twilight
 * @date 2019-02-06 14:50
 */
@Getter
public enum FileEnum {

    // 图片大小限制
    IMAGE_TOO_LARGE(400, "图片过大, 请使用小于5MB的图片上传"),

    // 图片格式限制
    IMAGE_FORMAT_ERROR(401, "请使用格式为.jpg或.png的图片"),

    // 文件创建失败
    CREATE_FILE_FAILED(402, "创建文件失败"),

    // 服务器
    SERVER_BUSY(403, "服务器繁忙"),

    DELETE_FILE_FAILED(404, "删除文件失败");

    private Integer code;

    private String message;

    FileEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
