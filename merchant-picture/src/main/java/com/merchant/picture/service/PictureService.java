package com.merchant.picture.service;

import org.springframework.web.multipart.MultipartFile;

public interface PictureService {

    /**
     * 上传照片
     *
     * @param file
     * @param type
     * @return
     */
    String UpdateImage(MultipartFile[] file, String type);

    String GetToken();
}
