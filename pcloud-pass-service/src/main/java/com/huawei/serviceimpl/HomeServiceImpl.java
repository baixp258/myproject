package com.huawei.serviceimpl;

import com.huawei.entity.Product;
import com.huawei.productmapper.ProductMapper;
import com.huawei.service.HomeService;
import com.huawei.status.BaseController;
import com.huawei.status.Response;
import com.huawei.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName HomeServiceImpl.java
 * @Description TODO
 * @createTime 2021年11月26日 15:31:00
 */
@Service
public class HomeServiceImpl extends BaseController implements HomeService {

    //访问量key
    public static final String visit_key="visit_total";


    @Resource
    private ProductMapper productMapper;
    @Override
    public Response getStatisticsVisitByCurrentTime(String currentTime) {
        if(StringUtils.isEmpty(currentTime)){
            return rtnFail("参数为null");
        }

     try {
         Jedis jedis= RedisUtils.getJedis().getResource();
         //获取当前时间访问量
         String currentNum = jedis.get(currentTime);
         //获取历史访问量
         String totalNum= jedis.get(visit_key);
         List<Integer> result= new ArrayList<>();
         result.add(Integer.valueOf(currentNum==null? "0": currentNum));
         result.add(Integer.valueOf(totalNum));
         return rtnSuccess(result);
     }catch (Exception e){
            e.printStackTrace();
            return rtnFail("查询失败");
     }

    }

    @Override
    public Response getSalesVolumet() {
        List<Product> products = productMapper.selectProducts();
        if(products.size()==0){
            return rtnSuccess();
        }
        //获取数据
        List<Object> result=new ArrayList<>();
        List<String> proName= new ArrayList<>();
        List<Integer> salesNum= new ArrayList<>();
        List<Integer> storeNum= new ArrayList<>();
        //解构数据获取产品名称、销售数量、库存数量
        for (Product pro:products) {
           proName.add(pro.getPname());
           salesNum.add(pro.getSalesVolume());
           storeNum.add(pro.getStockQuantity());
        }
        result.add(proName);
        result.add(salesNum);
        result.add(storeNum);
        return rtnSuccess(result);
    }
}
