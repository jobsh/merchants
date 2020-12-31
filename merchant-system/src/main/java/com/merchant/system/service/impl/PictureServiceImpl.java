package com.merchant.system.service.impl;

import com.merchant.common.utils.Auth;
import com.merchant.common.utils.GetLocalIp;
import com.merchant.common.utils.ImgCompress;
import com.merchant.common.utils.renturn.MyException;
import com.merchant.system.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service
public class PictureServiceImpl implements PictureService {

    private static final String basePath = "F:";

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    private static final String ak = "OT-H2cdLwy8zHtdVj7GdWGm3iTpSdMcp5W2S383d";
    private static final String sk = "vS_8IIa4uOt3Wszx77KAAaaJ1yrutpdHkpEYWmOq";
    private static final String bucket = "1jia2pic";

    @Autowired
    private ImgCompress imgCompress;

    @Override
    public String UpdateImage(MultipartFile[] files, String type) {
        StringBuffer imageUrl = null;
        if (null != files) {
            Random random = new Random();
            String oldName;
            StringBuilder newName;
            String imageType;
            String path;
            for (MultipartFile file1 : files) {
                newName = new StringBuilder();
                newName.append(new Date().getTime());
                newName.append(random.nextInt(1000));
                oldName = file1.getOriginalFilename();
                imageType = oldName.substring(oldName.lastIndexOf("."), oldName.length());
                newName.append(imageType);
                try {
                    path = basePath + File.separator + simpleDateFormat.format(new Date()) + File.separator + type;
                    imgCompress.storePicture(file1, 0.8, path, newName.toString());
                    String ip = GetLocalIp.getLocalIP();
                    ip = "172.31.210.232";
                    if ("172.31.210.232".equals(ip)) {
                        ip = "60.28.246.71";
                    } else if ("172.31.210.233".equals(ip)) {
                        ip = "60.28.246.105";
                    }
                    if (null == imageUrl) {
                        imageUrl = new StringBuffer();
                        imageUrl.append("http://" + ip + ":8091/" + simpleDateFormat.format(new Date()) + File.separator + type + "/" + newName);
                    } else {
                        imageUrl.append(";");
                        imageUrl.append("http://" + ip + ":8091/" + simpleDateFormat.format(new Date()) + File.separator + type + "/" + newName);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new MyException("图片上传失败！");
                }
            }
        }
        return imageUrl.toString();
    }

    @Override
    public String GetToken() {
        Auth auth = Auth.create(ak,sk);
        String token = auth.uploadToken(bucket);
        return token;
    }


}
