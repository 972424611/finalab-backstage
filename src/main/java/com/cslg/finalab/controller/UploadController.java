package com.cslg.finalab.controller;

import com.cslg.finalab.beans.JsonData;
import com.cslg.finalab.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


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
     * 上传项目封面图片
     */
    @ResponseBody
    @RequestMapping(value = "/image/project")
    public JsonData project(@RequestParam("image") MultipartFile file,
                            @RequestParam("id") Integer id) {
        uploadService.uploadProjectImage(file, id);
        return JsonData.success();
    }

    /**
     * 批量上传项目图片
     */
    @ResponseBody
    @RequestMapping(value = "/image/projectBatch")
    public JsonData projectBatch(@RequestParam("images") MultipartFile[] files,
                                 @RequestParam("id") Integer id) {
        uploadService.batchUploadProjectImage(files, id);
        return JsonData.success();
    }

    /**
     * 上传成员图片
     */
    @ResponseBody
    @RequestMapping(value = "/image/member")
    public JsonData member(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("image");
        String id = request.getParameter("id");
        uploadService.uploadMemberImage(file, Integer.valueOf(id));
        return JsonData.success();
    }

    /**
     * 批量上传团队图片
     */
    @ResponseBody
    @RequestMapping(value = "/image/memoryBatch")
    public JsonData memoryBatch(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> fileList = multipartRequest.getFiles("images");
        String remark = request.getParameter("remark");
        uploadService.batchUploadMemoryImage(fileList, remark);
        return JsonData.success();
    }

    /**
     * 上传获奖图片
     */
    @ResponseBody
    @RequestMapping(value = "/image/winning")
    public JsonData winning(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("image");
        String id = request.getParameter("id");
        uploadService.uploadWinningImage(file, Integer.valueOf(id));
        return JsonData.success();
    }
}
