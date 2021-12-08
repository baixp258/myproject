package com.huawei.reactcontroller;


import com.huawei.service.HomeService;
import com.huawei.status.BaseController;
import com.huawei.status.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName HomeController.java
 * @Description TODO
 * @createTime 2021年11月26日 15:23:00
 */
@Api("统计接口")
@CrossOrigin
@RestController
public class HomeController extends BaseController {

    @Autowired
    private HomeService homeService;

    @ApiOperation(value="getStatisticsVisit", notes="统计访问量")
    @ApiImplicitParam(name= "getStatisticsVisit", value = "统计访问量", required = false, dataType = "test")
    @RequestMapping(value = "/getStatisticsVisit",method = RequestMethod.GET)
    public Response statisticsVisit(@RequestParam String currentTime){
        //检验参数
        if(StringUtils.isEmpty(currentTime)){
            return rtnFail("请求参数为null");
        }
        return homeService.getStatisticsVisitByCurrentTime(currentTime.replace("/","-"));
    }

    @ApiOperation(value="salesVolumet", notes="商品销量/库存")
    @ApiImplicitParam(name= "salesVolumet", value = "商品销量/库存", required = false, dataType = "test")
    @RequestMapping(value = "/salesVolumet",method = RequestMethod.GET)
    public Response salesVolumet(){
        //检验参数
        return homeService.getSalesVolumet();
    }

}
