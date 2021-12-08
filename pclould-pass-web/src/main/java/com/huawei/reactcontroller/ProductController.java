package com.huawei.reactcontroller;


import com.huawei.entity.Product;
import com.huawei.service.ProductService;
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
 * @ClassName CategoryController.java
 * @Description TODO
 * @createTime 2021年10月15日 15:14:00
 */
@Api("产品展示")
@CrossOrigin
@RestController
@RequestMapping("/manager")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;



    @ApiOperation(value="product", notes="product产品展示")
    @ApiImplicitParam(name= "product", value = "product展品展示", required = false, dataType = "test")
    @RequestMapping(value = "/product/list",method = RequestMethod.POST)
    public Response productList(){
        //检验参数
      /*  if(product==null){
            return rtnFail("请求参数为null");
        }*/
        return productService.queryProductList();
    }

    @ApiOperation(value="product", notes="分页查询product产品展示")
    @ApiImplicitParam(name= "product", value = "分页查询product产品展示", required = false, dataType = "test")
    @RequestMapping(value = "/product/list/limit",method = RequestMethod.GET)
    public Response productListLimit(@RequestParam(value = "pageNum",defaultValue = "0") int pageNum,@RequestParam(value = "pageSize",defaultValue = "2") int pageSize){
        //检验参数
      if(StringUtils.isEmpty(String.valueOf(pageNum)) || StringUtils.isEmpty(String.valueOf(pageSize))){
            return rtnFail("请求参数为null");
        }
        return productService.queryProductListOfLimit(pageNum,pageSize);
    }


    @ApiOperation(value="limitAndCondition", notes="product产品展示")
    @ApiImplicitParam(name= "limitAndCondition", value = "product展品展示", required = false, dataType = "test")
    @RequestMapping(value = "/productList/limitAndCondition",method = RequestMethod.GET)
    public Response productListAndCondition(@RequestParam(value = "pageNum",defaultValue = "0") int pageNum,@RequestParam(value = "pageSize",defaultValue = "2") int pageSize,String productName,String productDesc){
        //检验参数
        if(StringUtils.isEmpty(String.valueOf(pageNum)) || StringUtils.isEmpty(String.valueOf(pageSize))){
            return rtnFail("请求参数为null");
        }
        if(StringUtils.isEmpty(productName) && StringUtils.isEmpty(productDesc)){
            return rtnFail("请求参数为null");
        }
        return productService.queryProductListAndCondition(pageNum,pageSize,productName,productDesc);
    }


    @ApiOperation(value="findProductPcategoryByPId", notes="产品类别查询")
    @ApiImplicitParam(name= "findProductPcategoryByPId", value = "产品类别查询", required = false, dataType = "test")
    @RequestMapping(value = "/findProductPcategoryByPId",method = RequestMethod.GET)
    public Response findProductPcategoryByPId(@RequestParam(value = "pcategoryId")String pcategoryId){
        //检验参数
        if(StringUtils.isEmpty(String.valueOf(pcategoryId))){
            return rtnFail("请求参数为null");
        }

        return productService.findProductPcategoryByPId(pcategoryId);
    }

    @ApiOperation(value="findProductCategoryById", notes="产品子类别查询")
    @ApiImplicitParam(name= "findProductCategoryById", value = "产品子类别查询", required = false, dataType = "test")
    @RequestMapping(value = "/findProductCategoryById",method = RequestMethod.GET)
    public Response findProductCategoryById(@RequestParam(value = "categoryId")String categoryId){
        //检验参数
        if(StringUtils.isEmpty(String.valueOf(categoryId))){
            return rtnFail("请求参数为null");
        }
        return productService.findProductCategoryById(categoryId);
    }

    @ApiOperation(value="updateProductStatusById", notes="产品修改状态")
    @ApiImplicitParam(name= "updateProductStatusById", value = "产品修改状态", required = false, dataType = "test")
    @RequestMapping(value = "/updateProductStatusById",method = RequestMethod.GET)
    public Response updateProductStatusById(@RequestParam(value = "id")String id,@RequestParam(value = "status") int status){
        //检验参数
        if(StringUtils.isEmpty(String.valueOf(status)) || StringUtils.isEmpty(id)){
            return rtnFail("请求参数为null");
        }
        return productService.updateProductStatusById(id,status);
    }


    @ApiOperation(value="addProduct", notes="产品添加")
    @ApiImplicitParam(name= "addProduct", value = "产品添加", required = false, dataType = "test")
    @RequestMapping(value = "/addProduct",method = RequestMethod.POST)
    public Response addProduct(@RequestBody Product product){
        //检验参数
        if(product==null){
            return rtnFail("请求参数为null");
        }
        return productService.addProduct(product);
    }


    @ApiOperation(value="updateProductById", notes="修改产品信息")
    @ApiImplicitParam(name= "updateProductById", value = "修改产品信息", required = false, dataType = "test")
    @RequestMapping(value = "/updateProductById",method = RequestMethod.POST)
    public Response updateProductById(@RequestBody Product product){
        if(StringUtils.isEmpty(product.getId())){
            return rtnFail("请求参数为null");
        }
        return productService.updateProduct(product);
    }

}
