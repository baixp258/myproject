package com.huawei.service;

import com.huawei.status.Response;

/**
 * @author admin
 * @title: HomeService
 * @projectName pcloudpassparent
 * @description: TODO
 * @date 2021/11/2615:29
 */
public interface HomeService {
    //获取访问量
    public Response getStatisticsVisitByCurrentTime(String currentTime);


    //获取商品销量、库存
    public Response getSalesVolumet();

}
