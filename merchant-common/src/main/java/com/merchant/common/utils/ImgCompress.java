package com.merchant.common.utils;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;

@Component
public class ImgCompress {

    private Image img;
    private int width;
    private int height;

    public void storePicture(MultipartFile file, double ratio, String path, String name) throws IOException {
        File destFile = new File(path);
        if (!destFile.exists()) {
            destFile.mkdirs();
        }
        File nfile = new File(path + File.separator + name);
            if (!nfile.exists()) {
            nfile.createNewFile();
        }
        Thumbnails.of(file.getInputStream()).scale(ratio).toFile(path + File.separator + name);
    }

//    img = ImageIO.read(file.getInputStream());
//    long l = file.getSize();
//    width = img.getWidth(null);
//    height = img.getHeight(null);
//    int w = width;
//    int h = height;
//        if (l / 1024 > 5120) {
//        w = (int) (width * ratio);
//        h = (int) (height * ratio);
//    }
//    BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
//        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图

//    FileOutputStream out = new FileOutputStream(nfile); // 输出到文件流
//        ImageIO.write(ImageIO.read(file.getInputStream()), "png", out);
//        out.close();

}


