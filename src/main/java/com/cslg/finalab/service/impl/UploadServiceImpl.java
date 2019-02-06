package com.cslg.finalab.service.impl;

import com.cslg.finalab.dao.SysProjectMapper;
import com.cslg.finalab.enums.UploadEnum;
import com.cslg.finalab.exception.UploadException;
import com.cslg.finalab.service.UploadService;
import com.google.common.collect.Sets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

/**
 * @author Twilight
 * @date 2019-02-06 14:41
 */
public class UploadServiceImpl implements UploadService {

    @Value("${image.address}")
    private String imageAddress;

    /**  图片最大大小 */
    private static final long MAXSIZE = 5242880;

    private static Set<String> imageFormatSet = Sets.newHashSet();

    static {
        imageFormatSet.add(".jpg");
        imageFormatSet.add(".png");
    }

    @Autowired
    private SysProjectMapper sysProjectMapper;

    private String getFormatAndTeam(FileItem item) {
        long size = item.getSize();
        // 设置上传的最大值5MB，5*1024*1024
        if(size > MAXSIZE) {
            throw new UploadException(UploadEnum.IMAGE_TOO_LARGE);
        }
        String fileName = item.getName();
        String format = fileName.substring(fileName.lastIndexOf("."));
        // 检查格式
        if(!imageFormatSet.contains(format)) {
            throw new UploadException(UploadEnum.IMAGE_FORMAT_ERROR);
        }
        return format;
    }

    private File createFile(String pathName) {
        File file = new File(pathName);
        if(!file.getParentFile().exists()) {
            if(!file.getParentFile().mkdirs()) {
                throw new UploadException(UploadEnum.CREATE_FILE_FAILED);
            }
        }
        if(!file.exists()) {
            try {
                if(file.createNewFile()) {
                    return file;
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new UploadException(UploadEnum.CREATE_FILE_FAILED);
            }
        }
        return file;
    }

    private void saveProjectImage(StringBuilder imagePaths, String projectId) {
        String[] paths = imagePaths.toString().split(" ");
        String coverImage = paths[0];
        String images = paths[1];
        int id = Integer.parseInt(projectId);
        sysProjectMapper.updateImageByProjectId(coverImage, images, id);
    }

    @Override
    public void uploadImage(HttpServletRequest request) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        StringBuilder imagePaths = new StringBuilder();
        try {
            // 得到所有文件
            List<FileItem> list = upload.parseRequest(request);
            String projectId = request.getParameter("id");
            // 依次处理每个上传的文件
            for(int i = 0; i < list.size(); i++) {
                FileItem item = list.get(i);
                // 判断是否是普通标单项，必须是enctype="multipart/form-data"
                if(!item.isFormField()) {
                    String format = getFormatAndTeam(item);
                    inputStream = item.getInputStream();
                    byte[] buffer = new byte[1024];
                    String pathName = imageAddress + projectId + "/" + i + format;
                    File file = createFile(pathName);
                    fileOutputStream = new FileOutputStream(file);
                    int len;
                    while((len = inputStream.read(buffer)) > 0) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    imagePaths.append(pathName);
                    if(i == 0) {
                        imagePaths.append(" ");
                    } else if(i != list.size() - 1) {
                        imagePaths.append(",");
                    }
                }
            }
            saveProjectImage(imagePaths, projectId);
        } catch (FileUploadException | IOException e) {
            e.printStackTrace();
            throw new UploadException(UploadEnum.SERVER_BUSY);
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                    if(fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
