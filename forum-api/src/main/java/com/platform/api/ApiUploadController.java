package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.oss.OSSFactory;
import com.platform.util.ApiBaseAction;
import com.platform.utils.RRException;
import com.platform.utils.ResourceUtil;
import com.platform.utils.upload.FileUpload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-09-08 13:20<br>
 * 描述: ApiUploadController <br>
 */
@Api(tags = "上传")
@RestController
@RequestMapping("/api/upload")
public class ApiUploadController extends ApiBaseAction {

    /**
     * 上传文件
     */
    @RequestMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }

        FileUpload fileUpload = new FileUpload();
        String originalName = file.getOriginalFilename();
        int dot = originalName.lastIndexOf('.');
        String suffix = ".jpg";
        if ((dot > -1) && (dot < (originalName.length() - 1))) {
            suffix = originalName.substring(dot + 1);
        }
        String fileName = DateTime.now().getMillis() + "." + suffix;

        String articleUpload = ResourceUtil.getConfigByName("article.upload");
        String path = request.getSession().getServletContext().getRealPath("/"+articleUpload) + File.separator;
        File transFile = fileUpload.transMultiFileToFile(path, fileName, file);


        //上传文件
        String url = articleUpload + File.separator + fileName;
        return toResponsSuccess(url);
    }
}