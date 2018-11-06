package com.platform.utils.upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by qingfeilee on 2015/4/25.
 */
public class FileUpload {
    public boolean createUploadFile(String path, String fileName, MultipartFile file){
        boolean flag = true;
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    public File transMultiFileToFile(String path, String fileName, MultipartFile file){
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return targetFile;
    }

    public static boolean deleteFile(String path){
        boolean flag = false;
        File file = new File(path);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

}
