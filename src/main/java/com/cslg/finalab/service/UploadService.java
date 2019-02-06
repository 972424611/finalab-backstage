package com.cslg.finalab.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Twilight
 * @date 2019-02-06 13:56
 */
public interface UploadService {

    /**
     * 上传图片
     * @param request 图片资源
     */
    void uploadImage(HttpServletRequest request);
}
