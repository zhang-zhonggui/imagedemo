package com.zzg.imagedemo.service.impl;

import cn.hutool.core.date.DateUtil;
import com.zzg.imagedemo.domain.Image;
import com.zzg.imagedemo.mapper.ImageMapper;
import com.zzg.imagedemo.service.ImageService;
import com.zzg.imagedemo.util.GetImageUtil;
import com.zzg.imagedemo.util.GetURL;
import com.zzg.imagedemo.util.OSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.zzg.imagedemo.util.GetImageUtil.downLoadFromUrl;

/**
 * @author ：zzg
 * @description：
 * @date ：2022/6/9 21:33
 */
@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageMapper imgManager;


    @Override
    public synchronized String addImage() {
        /**
         * 提取图片路径
         */
       // String url = GetURL.getMuXiAoGuo("https://api.muxiaoguo.cn/api/meinvtu?api_key=8f7a6f99b7425c71&num=1");
        String url = GetURL.getXioaBai();
        /**
         *下载文件
         */
        String s = UUID.randomUUID() +".jpg";
        downLoadFromUrl(url, s, "./img");
        /**
         * 上传文件
         * static String objectName = "3e022a6e-848a-4e2a-90c0-f31f52a74b01.jpg";
         * static String filePath = "./img/3e022a6e-848a-4e2a-90c0-f31f52a74b01.jpg";
         */
        String filePath = "./img/" + s;
        String objectName = s;
        String imageUrl = OSSUtil.uploadfile(filePath, objectName);
        Image image = new Image();
        image.setImageUrl(imageUrl);
        imgManager.addImage(image);

        /**
         * 删除文件
         * deleteFile
         */
        boolean b = GetImageUtil.deleteFile(filePath);
        System.out.println(b);


        /**
         * 返回图片路径
         */

      //  addImage();
        return imageUrl;
    }
}
