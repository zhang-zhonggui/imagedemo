package com.zzg.imagedemo.service.impl;

import com.zzg.imagedemo.mapper.ImageMapper;
import com.zzg.imagedemo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author ：zzg
 * @description：
 * @date ：2022/6/9 21:33
 */
@Service
public class ImageServiceImpl implements ImageService {


    @Autowired
    private ImageMapper imgManager;


}
