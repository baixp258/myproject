package com.huawei.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UploadImgUtils.java
 * @Description TODO
 * @createTime 2021年11月01日 09:13:00
 */
@Slf4j
public class UploadImgUtils {

    /**
     * 校验单张图片上传格式
     */
    public static boolean isCheckImg(MultipartFile uploadFile){
        //上传标识
        boolean isFlag = false;
        //比较上传图片的格式是否正确
        for (String type : BaseCanstants.ALLOWFILES_TYPE) {
            if (StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(), type)) {
                isFlag = true;
                break;
            }
        }
        return isFlag;
    }

    /**
     * 校验多单张图片上传格式
     */
    public static boolean isCheckImgs(MultipartFile[] uploadFile){
        //上传标识
        boolean isFlag = false;
        //比较上传图片的格式是否正确
        //验证上传图片格式
        for (MultipartFile loadFile : uploadFile) {
            for (String type : BaseCanstants.ALLOWFILES_TYPE) {
                //图片格式符合要求，截止
                if (StringUtils.endsWithIgnoreCase(loadFile.getOriginalFilename(), type)) {
                    isFlag = true;
                    break;
                }
            }
        }
        return isFlag;
    }

    /**
     * 将inputStream写入文件
     * @param stream 文件流
     * @param path   要写入的文件路径
     */
    public static void write(InputStream stream, String path) {
        try {
            FileUtils.copyInputStreamToFile(stream, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public static String  getNewFileName(MultipartFile multipartFile,File file ){
        //存放上传文件的文件夹
        //File file = new File("E:\\images" + format);
        log.info("-----------存放上传文件的文件夹【" + file + "】-----------");
        if (!file.isDirectory()) {
            //递归生成文件夹
            file.mkdirs();
        }
        //获取原始的名字  original:最初的，起始的  方法是得到原来的文件名在客户机的文件系统名称
        String oldName = multipartFile.getOriginalFilename();
        log.info("-----------文件原始的名字【" + oldName + "】-----------");
        String newName = getNewPictureName(oldName);
        log.info("-----------文件要保存后的新名字【" + newName + "】-----------");
        return newName;
    }
    /**
     * 获取新图片的名称
     */
    public static String getNewPictureName(String oldName){
        if (StringUtils.isEmpty(oldName)) {
            return null;
        }
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
        return newName;
    }

    /**
     * 校验文件是否是标准文件格式
     */
    /**
     * 校验多单张图片上传格式
     */
    public static boolean isCheckImgs(String imgName){
        //上传标识
        boolean isFlag = false;
        if(StringUtils.isEmpty(imgName)){
            return isFlag;
        }
        //获取被删除图片的格式（.jpg / .png ）
        StringBuffer img = new StringBuffer();
        String imgNameEndsWith=img.append(".").append(imgName.split("\\.")[1]).toString();
        //验证上传图片格式
            for (String type : BaseCanstants.ALLOWFILES_TYPE) {
                //图片格式符合要求，截止
                if (StringUtils.endsWithIgnoreCase(imgNameEndsWith, type)) {
                    isFlag = true;
                    break;
                }
            }
        return isFlag;
    }



}
