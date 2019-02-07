package com.cslg.finalab.controller;

import com.cslg.finalab.beans.JsonData;
import com.cslg.finalab.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


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
     * 上传图片
     */
    @ResponseBody
    @RequestMapping(value = "/project/image")
    public JsonData image(@RequestParam("image") MultipartFile file, @RequestParam("id") Integer id) {
        uploadService.uploadImage(file, id);
        return JsonData.success();
    }

    /**
     * 批量上传
     */
    @ResponseBody
    @RequestMapping(value = "/project/batchImage")
    public JsonData batchImage(@RequestParam("images") MultipartFile[] file, @RequestParam("id") Integer id) {
        uploadService.batchUploadImage(file, id);
        return JsonData.success();
    }
}
