package com.cslg.finalab.controller;

import com.cslg.finalab.beans.JsonData;
import com.cslg.finalab.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Twilight
 * @date 2019-02-06 13:54
 */
@Controller
@RequestMapping(value = "/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * 上传图片，可批量
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/picture")
    public JsonData imageUpload(HttpServletRequest request) {
        uploadService.uploadImage(request);
        return JsonData.success();
    }
}
