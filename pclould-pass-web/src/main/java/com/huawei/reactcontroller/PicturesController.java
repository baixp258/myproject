package com.huawei.reactcontroller;

import com.huawei.service.UplpoadImgService;
import com.huawei.status.BaseController;
import com.huawei.status.Response;
import com.huawei.utils.BaseCanstants;
import com.huawei.utils.UploadImgUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName PicturesController.java
 * @Description 实现单图片和多图片上传
 * @createTime 2021年10月29日 14:39:00
 */
@Slf4j
@RequestMapping("/upload")
@RestController
@CrossOrigin
public class PicturesController extends BaseController {


    @Autowired
    private UplpoadImgService uplpoadImgService;


     @ApiOperation(value="singleImg", notes="图片上传接口")
     @ApiImplicitParam(name= "singleImg", value = "图片上传接口", required = false, dataType = "test")
     @RequestMapping(value = "/singleImg",method = RequestMethod.POST)
     public Response uplodadSingleImg(@RequestParam("uploadFile") MultipartFile uploadFile, HttpServletRequest request){

      // 校验参数
         if(uploadFile.isEmpty()){
             return rtnFail("上传内容为null");
         }
         // 验证图片上传格式
         boolean isFlag = UploadImgUtils.isCheckImg(uploadFile);
         //如果正确上传
          if (isFlag) {
              //调用单张图片上传的接口
            return uplpoadImgService.uploadingImgService(uploadFile, request);
          } else {//
              return rtnFail("上传图片格式异常");
          }
  }


    @ApiOperation(value="uploadManyImg", notes="多张图片上传接口")
    @ApiImplicitParam(name= "uploadManyImg", value = "多张图片上传接口", required = false, dataType = "test")
    @RequestMapping(value = "/uploadManyImg",method = RequestMethod.POST)
    public Response uploadManyImg(@RequestParam("uploadFile") MultipartFile[] uploadFile, HttpServletRequest request){
         //参数校验
      if(uploadFile==null || uploadFile.length==0){
          return rtnFail("上传内容为null");
      }
      //验证上传图片格式
        boolean isFlag = UploadImgUtils.isCheckImgs(uploadFile);
        //验证合格调用多张图片上传接口上传图片
       if (isFlag) {
          return uplpoadImgService.uploadingImgsService(uploadFile, request);
       } else {
           return rtnFail("上传图片格式异常");
       }
   }

    @ApiOperation(value="deleteImg", notes="图片删除接口")
    @ApiImplicitParam(name= "deleteImg", value = "图片删除接口", required = false, dataType = "test")
    @RequestMapping(value = "/deleteImg",method = RequestMethod.GET)
    public Response deleteImg(@RequestParam("imgName") String imgName){
        //参数校验
        if(StringUtils.isEmpty(imgName)){
            return rtnFail("参数为null");
        }
        //验证删除图片格式
        boolean isFlag = UploadImgUtils.isCheckImgs(imgName);
        //校验合格删除图片
        if (isFlag) {
            return uplpoadImgService.deleteImgService(BaseCanstants.BASEFILEPATH,imgName);
        }
        return rtnFail("图片删除失败");

    }


}
