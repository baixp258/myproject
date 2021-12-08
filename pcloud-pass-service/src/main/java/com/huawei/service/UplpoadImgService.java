package com.huawei.service;

import com.huawei.status.Response;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UplpoadImgService.java
 * @Description TODO
 * @createTime 2021年10月29日 14:46:00
 */
public interface UplpoadImgService {

    /**
     * 单张图片上传
     */
    public Response uploadingImgService(MultipartFile uploadFile, HttpServletRequest request);



    /**
     * 多张图片上传
     */
    public Response uploadingImgsService(MultipartFile[] uploadFile, HttpServletRequest request);


    /**
     * 删除图片接口
     */
    public Response deleteImgService(String path,String imgName);
}
