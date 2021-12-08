package com.huawei.status;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Response.java
 * @Description TODO
 * @createTime 2021年09月22日 09:46:00
 */

@Data
@ToString
@NoArgsConstructor
public class Response {

    //相应状态码
    private String Code;

    //相应描述
    private String Msg;

    //响应内容
    private Object data;



}
