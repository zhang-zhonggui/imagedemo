package com.zzg.imagedemo.controller;

import cn.hutool.core.date.DateUtil;
import com.zzg.imagedemo.domain.Image;
import com.zzg.imagedemo.mapper.ImageMapper;
import com.zzg.imagedemo.service.ImageService;
import com.zzg.imagedemo.util.GetImageUtil;
import com.zzg.imagedemo.util.GetURL;
import com.zzg.imagedemo.util.OSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.zzg.imagedemo.util.GetImageUtil.downLoadFromUrl;

/**
 * @author ：zzg
 * @description：
 * @date ：2022/6/9 21:31
 */
@RestController
@RequestMapping("image")
public class ImgController {

    @Autowired
    private ImageService imageService;

    @GetMapping("add")
    public Object addImage() {
        return imageService.addImage();

    }
}
