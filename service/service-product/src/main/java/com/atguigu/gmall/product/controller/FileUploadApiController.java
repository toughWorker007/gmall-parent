package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import lombok.SneakyThrows;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @desc
 */
@RestController
@CrossOrigin
@RequestMapping("/admin/product/")
public class FileUploadApiController {

    @SneakyThrows
    @PostMapping("fileUpload")
    public Result fileUpload(MultipartFile file){
        String url = "http://192.168.200.128:8080";
        String confPath = FileUploadApiController.class
                .getClassLoader().getResource("tracker.conf").getPath();
        ClientGlobal.init(confPath);
        String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String[] uploadFiles = new StorageClient(new TrackerClient().getConnection(), null)
                .upload_file(file.getBytes(), filenameExtension, null);
        for (String uploadFile : uploadFiles) {
            url = url + "/" + uploadFile;
        }
//        System.out.println(url);
        return Result.ok(url);
    }
}
