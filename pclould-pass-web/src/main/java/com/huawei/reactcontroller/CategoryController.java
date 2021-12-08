package com.huawei.reactcontroller;


import com.huawei.entity.Category;
import com.huawei.service.CategoryService;
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
@Api("商品类别")
@CrossOrigin
@RestController
@RequestMapping("/manager")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;



    @ApiOperation(value="category", notes="category类别管理")
    @ApiImplicitParam(name= "category", value = "category类别管理", required = false, dataType = "test")
    @RequestMapping(value = "/categoryList",method = RequestMethod.POST)
    public Response categoryList(@RequestBody Category category){
        //检验参数
        if(StringUtils.isEmpty(category.getParentId())){
            return rtnFail("请求参数为null");
        }
        return categoryService.findCategoryByParentId(category.getParentId());
    }

    @ApiOperation(value="categoryAdd", notes="category类别管理添加")
    @ApiImplicitParam(name= "categoryAdd", value = "category类别管理添加", required = false, dataType = "test")
    @RequestMapping(value = "/categoryAdd",method = RequestMethod.POST)
    public Response categoryAdd(@RequestBody Category category){
        //检验参数
        if(category==null){
            return rtnFail("请求参数为null");
        }
        return categoryService.findCategoryAdd(category);
    }

    @ApiOperation(value="categoryUpdate", notes="category类别管理修改")
    @ApiImplicitParam(name= "categoryUpdate", value = "category类别管理修改", required = false, dataType = "test")
    @RequestMapping(value = "/categoryUpdate",method = RequestMethod.POST)
    public Response categoryUpdate(@RequestBody Category category){
        //检验参数
        if(category==null){
            return rtnFail("请求参数为null");
        }
        return categoryService.findCategoryUpdate(category);
    }


    @ApiOperation(value="categoryChildRenList", notes="category子类查询")
    @ApiImplicitParam(name= "categoryChildRenList", value = "category子类查询", required = false, dataType = "test")
    @RequestMapping(value = "/categoryChildRenList",method = RequestMethod.POST)
    public Response categoryChildRenList(@RequestBody Category category){
        //检验参数
        if(StringUtils.isEmpty(category.getParentId())){
            return rtnFail("请求参数为null");
        }
        return categoryService.findCategoryChildrenByParentId(category.getParentId());
    }
}
