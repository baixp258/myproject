package com.huawei.status;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName BaseController.java
 * @Description TODO
 * @createTime 2021年09月18日 17:10:00
 */
public class BaseController {


    /**
     * 返回成功
     */
    public  Response rtnSuccess(){
        return returnRes(CodeMsg.success_code,CodeMsg.success_msg,null);
    }

    /**
     * 返回成功
     */
    public  Response rtnSuccess(Object data){
        return returnRes(CodeMsg.success_code,CodeMsg.success_msg,data);
    }
    /**
     * 返回失败
     */
    public  Response rtnFail(){
        return returnRes(CodeMsg.fail_code,CodeMsg.fail_msg,null);
    }


    /**
     * 返回失败自带参数
     */
    public  Response rtnFail(String msg){
        return returnRes(CodeMsg.fail_code,msg,null);
    }


    /**
     * 缺少必要参数自带参数
     */
    public  Response rtnParmterMiss(){
        return returnRes(CodeMsg.parmeter_miss_code,CodeMsg.parmeter_miss_msg,null);
    }

    /**
     * 缺少必要参数自带参数
     */
    public  Response rtnParmterMiss(String msg){
        return returnRes(CodeMsg.parmeter_miss_code,msg,null);
    }


    /**
     * 基本封装相应实体
     */
    public Response returnRes(String code,String msg,Object obj){
        Response resp = new Response();
        resp.setCode(code);
        resp.setData(obj);
        resp.setMsg(msg);
        return resp;
    }
}
