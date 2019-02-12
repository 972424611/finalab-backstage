package com.cslg.finalab.common;

import com.cslg.finalab.enums.FileEnum;
import com.cslg.finalab.exception.FileException;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * @author Twilight
 * @date 2019-02-12 19:06
 */
public class FileOperation {

    /**  图片最大大小 */
    private static final long MAXSIZE = 5242880;

    private static Set<String> imageFormatSet = Sets.newHashSet();

    static {
        imageFormatSet.add(".jpg");
        imageFormatSet.add(".png");
    }

    public static File createFile(String pathName) {
        File file = new File(pathName);
        if(!file.getParentFile().exists()) {
            if(!file.getParentFile().mkdirs()) {
                throw new FileException(FileEnum.CREATE_FILE_FAILED);
            }
        }
        if(!file.exists()) {
            try {
                if(file.createNewFile()) {
                    return file;
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new FileException(FileEnum.CREATE_FILE_FAILED);
            }
        }
        return file;
    }

    public static void deleteDirectory(String pathName) {
        //如果pathName不以文件分隔符结尾，自动添加文件分隔符
        if(!pathName.endsWith(File.separator)) {
            pathName = pathName + File.separator;
        }
        File directoryFile = new File(pathName);
        //如果对应的文件不存在，或者不是一个目录，则退出
        if(!directoryFile.exists() || !directoryFile.isDirectory()) {
            return;
        }
        // 删除文件夹下的所有文件（包括子目录）
        File[] files = directoryFile.listFiles();
        if(files != null && files.length > 0) {
            for(File file : files) {
                if(file.isFile()) {
                    deleteFile(file.getAbsolutePath());
                } else {
                    deleteDirectory(file.getAbsolutePath());
                }
            }
        }
        // 删除当前目录
        if(!directoryFile.delete()) {
            throw new FileException(FileEnum.DELETE_FILE_FAILED);
        }
    }


    public static void deleteFile(String pathName) {
        File file = new File(pathName);
        //如果对应的文件不存在，或者不是一个文件，则退出
        if(!file.exists() || !file.isFile()) {
            return;
        }
        if(!file.delete()) {
            throw new FileException(FileEnum.DELETE_FILE_FAILED);
        }
    }

    public static void checkImageSize(long size) {
        // 设置上传的最大值5MB，5*1024*1024
        if(size > MAXSIZE) {
            throw new FileException(FileEnum.IMAGE_TOO_LARGE);
        }
    }

    public static String checkFileNameAndGetFormat(String fileName) {
        if(StringUtils.isBlank(fileName)) {
            throw new FileException(FileEnum.IMAGE_FORMAT_ERROR);
        }
        String format = fileName.substring(fileName.lastIndexOf("."));
        // 检查格式
        if(!imageFormatSet.contains(format)) {
            throw new FileException(FileEnum.IMAGE_FORMAT_ERROR);
        }
        return format;
    }

    public static void writeToFile(String pathName, MultipartFile multipartFile) {
        File file = createFile(pathName);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(FileEnum.SERVER_BUSY);
        }
    }
}
