package com.zzg.imagedemo.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DouYin {
   public static final   Integer MAX_BUFFER_SIZE= 1024*1024*200;


    public static String downVideo(String videoUrl, String downloadPath, String fileName, String SuffixName) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        RandomAccessFile randomAccessFile = null;

        //路径名加上文件名加上后缀名 = 整个文件下载路径
        String fullPathName = downloadPath + fileName + "." + SuffixName;

        try {
            // 1.获取连接对象
            URL url = new URL(videoUrl);
            // 获取链接对象，就是靠这个对象来获取流
            connection = (HttpURLConnection) url.openConnection();
            // Range代表读取的范围，bytes=0-代表从0字节到最大字节，意味着读取所有资源
            connection.setRequestProperty("Range", "bytes=0-");
            // 与网页建立链接，链接成功后就可以获得流；
            connection.connect();
            // 如果建立链接返回的相应代码是200到300间就为成功，否则链接失败,结束函数
            if (connection.getResponseCode() / 100 != 2) {
                System.out.println("连接失败...");

            }
            // 2.获取连接对象的流
            inputStream = connection.getInputStream();
            // 已下载的大小 下载进度
            int downloaded = 0;
            // 总文件的大小
            int fileSize = connection.getContentLength();
            // getFile获取此URL的文件名。返回的文件部分将与getPath（）相同,具体视频链接的文件名字视情况而定
            // String fileName = url.getFile();
            // fileName = fileName.substring(fileName.lastIndexOf("/") + 1);//特殊需要截取文件名字
            // 3.把资源写入文件
            randomAccessFile = new RandomAccessFile(fullPathName, "rw");
            while (downloaded < fileSize) {
                // 3.1设置缓存流的大小
                //判断当前剩余的下载大小是否大于缓存之，如果不大于就把缓存的大小设为剩余的。
                byte[] buffer = null;
                if (fileSize - downloaded >= MAX_BUFFER_SIZE) {
                    buffer = new byte[MAX_BUFFER_SIZE];
                } else {
                    buffer = new byte[fileSize - downloaded];
                }
                // 3.2把每一次缓存的数据写入文件
                int read = -1;
                int currentDownload = 0;
                long startTime = System.currentTimeMillis();
                // 这段代码是按照缓存的大小，读写该大小的字节。然后循环依次写入缓存的大小，直至结束。
                // 这样的优势在于，不用让硬件频繁的写入，可以提高效率和保护硬盘吧
                while (currentDownload < buffer.length) {
                    read = inputStream.read();
                    buffer[currentDownload++] = (byte) read;
                }
                long endTime = System.currentTimeMillis();
                double speed = 0.0; //下载速度
                if (endTime - startTime > 0) {
                    speed = currentDownload / 1024.0 / ((double) (endTime - startTime) / 1000);
                }
                randomAccessFile.write(buffer);
                downloaded += currentDownload;
                randomAccessFile.seek(downloaded);
                System.out.printf(fullPathName + "下载了进度:%.2f%%,下载速度：%.1fkb/s(%.1fM/s)%n", downloaded * 1.0 / fileSize * 10000 / 100,
                        speed, speed / 1000);
            }
            return fullPathName;

        } catch (MalformedURLException e) {// 具体的异常放到前面
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭资源、连接
                connection.disconnect();
                inputStream.close();
                randomAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
