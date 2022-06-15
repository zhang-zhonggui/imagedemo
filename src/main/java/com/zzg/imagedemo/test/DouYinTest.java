package com.zzg.imagedemo.test;

import com.zzg.imagedemo.util.OSSUtil;

import java.util.UUID;

public class DouYinTest {
    public static void main(String[] args) {
//
//        String uuid = UUID.randomUUID().toString();
//        String objectName = DouYin.downVideo("http://api.qemao.com/api/douyin/", "douyin", uuid, "mp4");

        String imageUrl = OSSUtil.uploadfile("./img/douyin6fe885a5-c55c-4163-b219-a0829ff8cbf8.mp4", "douyin6fe885a5-c55c-4163-b219-a0829ff8cbf8.mp4");
        System.out.println(imageUrl);
    }
}
