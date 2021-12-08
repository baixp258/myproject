package com.huawei.serviceimpl;

import com.huawei.service.UplpoadImgService;
import com.huawei.status.BaseController;
import com.huawei.status.Response;
import com.huawei.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author admin
 * @version 1.0.0
 * @ClassName UplpoadImgServiceImpl.java
 * @Description TODO
 * @createTime 2021年10月29日 14:50:00
 */
@Service
@Slf4j
public class UplpoadImgServiceImpl extends BaseController implements UplpoadImgService {


    @Override
    public Response uploadingImgService(MultipartFile uploadFile, HttpServletRequest request) {
        //存放图片到图片服务器，返回访问路径
        Response result = uploadImgMethod(uploadFile, request);
        if(result.getCode().equals("0000")){
          return rtnSuccess(result.getData());
        } else{
            return rtnFail("上传图片失败");
        }
    }

    @Override
    public Response uploadingImgsService(MultipartFile[] uploadFile, HttpServletRequest request) {
        //存放访问图片的url
        List<String> imgPaths = new ArrayList<>();
        for (MultipartFile multipartFile : uploadFile) {
            //存放图片到图片服务器，返回访问路径
            Response result = uploadImgMethod(multipartFile, request);
            if(result.getCode().equals("0000")){
                imgPaths.add((String)result.getData());
            } else{
                return rtnFail("上传图片失败");
            }
        }
        return rtnSuccess(imgPaths);

    }

    @Override
    public Response deleteImgService(String path, String imgName) {
        try{
            FileUtils.removeFile(path,imgName);
            return rtnSuccess("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return rtnFail("图片删除失败");
        }

    }

    /**
     * 上传图片方法类
     * @param multipartFile
     * @param request
     * @return
     */
    private Response uploadImgMethod(MultipartFile multipartFile, HttpServletRequest request) {
        if (multipartFile.isEmpty()) {
            //返回选择文件提示
            return rtnFail("上传文件为null");
        }
        //存放上传文件的文件夹
        File file = new File(BaseCanstants.BASEFILEPATH+DateUtils.getCurrentTime());
        //根据上传的文件获取新的获取新文件名称
        String newName = UploadImgUtils.getNewFileName(multipartFile,file);
        Map<String,String> imgUrlAndName = new HashMap<>();
        try {
            //构建真实的文件路径
            File newFile = new File( BaseCanstants.BASEFILEPATH+ DateUtils.getCurrentTime()+ newName);
            //转存文件到指定路径，如果文件名重复的话，将会覆盖掉之前的文件,这里是把文件上传到 “绝对路径”
            multipartFile.transferTo(newFile);
            //请求图片地址
            String reqPictureUrl=getPictureUrl(newFile,newName);
            //返回浏览器访问地址
           log.info("-----------【" + reqPictureUrl + "】-----------");
            imgUrlAndName.put("name",newName);
            imgUrlAndName.put("url",reqPictureUrl);
            return rtnSuccess(imgUrlAndName);
        } catch (Exception e) {
            e.printStackTrace();
            return rtnFail("上传失败");

        }
    }

    /**
     * 获取请求图片的url
     */
    private String getPictureUrl(File newFile,String newName){
        String reqPictureUrl=null;
        //判断图片存放本地还是服务器，返回相应的url
        if(newFile.equals(BaseCanstants.BASEFILEPATH)){
             reqPictureUrl=  "http://" + GetIpAddressUtil.getIpAddress() + BaseCanstants.REQPICTUREPROJECTURL+DateUtils.getCurrentTime() + newName;
        }else{
            reqPictureUrl= "http://" + GetIpAddressUtil.getIpAddress() + BaseCanstants.REQPICTURESERVERURL+DateUtils.getCurrentTime() + newName;
        }
        return  reqPictureUrl;
    }

}
