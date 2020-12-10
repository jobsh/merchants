package com.merchant.picture.controller;

import com.merchant.picture.service.PictureService;
import com.merchant.util.renturn.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping(value = "image")
public class PictureController {

    @Autowired
    private PictureService pictureService;

//    @ApiOperation(value = "上传客户的个人信息图片")
    @CrossOrigin
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Result TraderUpdate(@RequestParam("file") MultipartFile[] file, @RequestParam("type") String type) {
        String urls = pictureService.UpdateImage(file, type);
        return Result.success("上传成功!", urls);
    }

//    @ApiOperation(value = "上传客户的个人信息图片")
    @CrossOrigin
    @GetMapping(value = "getToken")
    public Result getToken() {
        String token = pictureService.GetToken();
        return Result.success(token);
    }
}
