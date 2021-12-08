package com.huawei.serviceimpl;

import com.huawei.entity.Category;
import com.huawei.entity.CategoryChildren;
import com.huawei.productmapper.CategoryChildrenMapper;
import com.huawei.productmapper.CategoryMapper;
import com.huawei.service.CategoryService;
import com.huawei.status.BaseController;
import com.huawei.status.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserServiceImpl.java
 * @Description TODO
 * @createTime 2021年09月22日 16:25:00
 */
@Service
public class CategoryServiceImpl extends BaseController implements CategoryService {

    //商品类别
    @Autowired
    private CategoryMapper categoryMapper;

    //商品类别子类
    @Autowired
    private CategoryChildrenMapper categoryChildrenMapper;

   
    
    /**
     * 查询商品类别
     * @param parentId
     * @return
     */
    @Override
    public Response findCategoryByParentId(String parentId) {
        if(StringUtils.isEmpty(parentId)){
            return rtnFail("参数为null");
        }
        List<Category> categorys= categoryMapper.findCategoryByParentId(parentId);
        return rtnSuccess(categorys);
    }

    @Override
    public Response findCategoryAdd(Category category) {
        String maxId=null;
        String id=null;
        int  addNum=0;
        if(StringUtils.isEmpty(category.getName()) || StringUtils.isEmpty(category.getParentId())){
            return rtnFail("一级分类添加内容为null");
        }
        //添加一级分类
        if(StringUtils.length(category.getParentId())==0){
              maxId = categoryMapper.queryCategotyMaxId();
            //获取category的id
             id = getCatogoryId(maxId);
            addNum = categoryMapper.addCategory(id,category.getName(),category.getParentId());
            if(addNum==0){
                return rtnFail("添加一级分类失败");
            }
            return rtnSuccess(addNum);
        }
        //添加子分类
          maxId = categoryChildrenMapper.queryCategotyChildrenMaxId();
          id =getCatogoryId(maxId);
          addNum = categoryChildrenMapper.addCategoryChildren(id,category.getName(),category.getParentId());
        if(addNum==0){
            return rtnFail("添加子分类失败");
        }
        return rtnSuccess(addNum);
    }

    /**
     * 修改一级分类
     * @param category
     * @return
     */
    @Override
    public Response findCategoryUpdate(Category category) {
        if(StringUtils.isEmpty(category.getName() ) || StringUtils.isEmpty(category.getId())){
            return rtnFail("一级分类修改内容为null");
        }
         int updateNum=categoryMapper.updateCategoryByParentId(category.getId(), category.getName());
        if(StringUtils.isEmpty(String.valueOf(updateNum)) || updateNum==0){
            return rtnFail("修改异常");
        }
        return rtnSuccess(updateNum);
    }

    /**
     * 查询商品子类
     * @param parentId
     * @return
     */
    @Override
    public Response findCategoryChildrenByParentId(String parentId) {
        if(StringUtils.isEmpty(parentId)){
            return rtnFail("参数为null");
        }
        List<CategoryChildren> categoryChildrens= categoryChildrenMapper.findCategoryChildrenByParentId(parentId);
        return rtnSuccess(categoryChildrens);
    }

    /**
     * 获取插入是Id
     */
    public String getCatogoryId(String maxId){
        if(StringUtils.isEmpty(maxId)){
            maxId="0001";
        }
        StringBuffer cotegoryAdd = new StringBuffer();
        StringBuffer categoryId = cotegoryAdd.append("000").append(String.valueOf(Integer.valueOf(maxId) + 1));
        return categoryId.toString();
    }


}
