package com.huawei.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName FileUtils.java
 * @Description TODO
 * @createTime 2021年11月01日 22:08:00
 */
@Slf4j
public class FileUtils {

    /**
     * 根据文件名称查找当前文件服务器的图片，并删除
     */
    public static void removeFile(String path,String imgName){
        //获取当前服务路径下所有文件夹
        File file=new File(path);
        File[] files = file.listFiles();
        if(files.length==0){
            return;
        }
        log.info("当前文件的还有子文件个数"+files.length);
        //遍历当前文件夹下是否还有文件
        for (File child : files){
            if(child.isDirectory()){
                //获取当前文件下是否还有文件
                File file1 = new File(child.getPath());
                   if(file1.listFiles().length==0){
                        return;
                    }
                    //获取当前文件下文件
                    File[] files2 = file1.listFiles();
                   //当前文件夹或者文件内容为null
                   if(files2.length==0){
                       break;
                   }
                log.info("当前的文夹含有的图片数量为==============="+file1.getName()+"<========>"+file1.listFiles().length+"张=================");
                    for(File sub : files2){
                        String name = sub.getName();
                        //包含当前删除的图片删除
                      if(name.equals(imgName)){
                        sub.delete();
                        break;
                       }
                       // log.info(name+"---------");

                }
                log.info("删除之后当前的文夹含有的图片数量为==============="+file1.getName()+"<========>"+file1.listFiles().length+"张=================");

            }

        }

    }

    public static void main(String[] args) {
        removeFile("/picture","ed1f7120-c0e7-46bc-a5b2-0940c5861558.jpg");
    }


}
