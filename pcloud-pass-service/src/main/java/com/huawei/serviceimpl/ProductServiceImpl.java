package com.huawei.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.huawei.entity.Category;
import com.huawei.entity.CategoryChildren;
import com.huawei.entity.Product;
import com.huawei.productmapper.ProductMapper;
import com.huawei.service.ProductService;
import com.huawei.status.BaseController;
import com.huawei.status.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserServiceImpl.java
 * @Description TODO
 * @createTime 2021年09月22日 16:25:00
 */
@SuppressWarnings("ALL")
@Service
public class ProductServiceImpl extends BaseController implements ProductService {


    @Resource
    private ProductMapper productMapper;


    @Override
    public Response queryProductList() {
        //获取所有商品列表信息
        List<Product> product1 = productMapper.findProduct();
        return rtnSuccess(product1);
    }

    @Override
    public Response queryProductListOfLimit(int pageNum, int pageSize) {
        //分页查询不带条件
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        Page<Product> products = productMapper.findAll(pageRequest);
        return rtnSuccess(products);
    }

    //条件查询带分页
    @Override
    public Response queryProductListAndCondition(int pageNum, int pageSize, String productName, String productDesc) {
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        //根据条件进行分页查询
        Page<Product> products = productMapper.findAll(new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //存放分页查询的的条件
                List<Predicate> predicate = new ArrayList<>();
                if(StringUtils.isNotEmpty(productName)){
                     //predicate.add(criteriaBuilder.equal(root.get("pname").as(String.class),productName)); //精确查找
                    //模糊匹配
                    predicate.add(criteriaBuilder.like(root.get("pname").as(String.class), "%" + productName + "%"));
                }else if(StringUtils.isNotEmpty(productDesc)){
                    //predicate.add(criteriaBuilder.equal(root.get("pdesc").as(String.class),productType));//精确查找
                    //模糊匹配
                    predicate.add(criteriaBuilder.like(root.get("pdesc").as(String.class), "%" + productDesc + "%"));
                }
                Predicate[] predicateArray= new Predicate[predicate.size()];
                return criteriaBuilder.and(predicate.toArray(predicateArray));
            }
        },pageRequest);
        /*Specification<Product> specification = new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //存放分页查询的的条件
                List<Predicate> predicate = new ArrayList<>();
                if(StringUtils.isNotEmpty(productName)){
                    predicate.add(criteriaBuilder.like(root.get("pname").as(String.class), "%" + productName + "%"));
                }else if(StringUtils.isNotEmpty(productType)){
                    predicate.add(criteriaBuilder.like(root.get("pdesc").as(String.class), "%" + productType + "%"));
                }
                Predicate[] predicateArray= new Predicate[predicate.size()];

                return criteriaBuilder.and(predicate.toArray(predicateArray));
            }
        };
        Page<Product> products =  productMapper.findAll(specification,pageRequest);*/

        return rtnSuccess(products);
    }

    /**
     * 根据产品Id查询产品父类别
     * @param pcategoryId
     * @return
     */
    @Override
    public Response findProductPcategoryByPId(String pcategoryId) {
        Map<String, Object>category1 = productMapper.findCategoryByParentId(pcategoryId);
        Category category = JSON.parseObject(JSON.toJSONString(category1), Category.class);
        return rtnSuccess(category);
    }

    /**
     * 根据产品Id查询产品所属类别
     * @param categoryId
     * @return
     */
    @Override
    public Response findProductCategoryById(String categoryId) {
        Map<String, Object> categoryChildren1 = productMapper.findCategoryChildrenByParentId(categoryId);
        CategoryChildren categoryChildren = JSON.parseObject(JSON.toJSONString(categoryChildren1), CategoryChildren.class);
        return rtnSuccess(categoryChildren);
    }

    /**
     * 根据产品ID修改产品状态（上架/下线）
     * @param id
     * @param update
     * @return
     */
    @Override
    public Response updateProductStatusById(String id, int status) {
        int updateNum = productMapper.updateProducStatusById(id, status);
        return rtnSuccess(updateNum);
    }

    /**
     *  添加产品
     * @param product
     * @return
     */
    @Override
    public Response addProduct(Product product) {
        try{
            //获取最大id
            String id = productMapper.queryProductMaxId();
            if(StringUtils.isEmpty(id)){
                return rtnFail("获取Id异常");
            }
            //组装插入数据id
            String productId = getProductId(id);
            product.setId(productId);
            //产品状态默认上线 0上线 1下线

            Integer insertNum = productMapper.addProduct(product.getId(), product.getPname(), product.getPdesc(), product.getPrice(),
                    product.getPCategoryId(),product.getCategoryId(), product.getImages(), product.getDetail(),product.getStatus());
            return rtnSuccess(insertNum);
        }catch (Exception e){
            e.printStackTrace();
            return rtnFail("添加产品异常");
        }
    }


    /**
     * 修改产品信息
     * @param product
     * @return
     */
    @Override
    public Response updateProduct(Product product) {
        try{
            Integer updateNum = productMapper.updateProduct(product.getId(), product.getPname(), product.getPdesc(), product.getPrice(),
                    product.getPCategoryId(), product.getCategoryId(), product.getImages(), product.getDetail());
            return rtnSuccess(updateNum);
        }catch (Exception e){
            e.printStackTrace();
            return rtnFail("修改产品异常");
        }
    }

    /**
     * 获取插入是Id
     */
    public String getProductId(String maxId){
        if(StringUtils.isEmpty(maxId)){
            maxId="01";
        }
        StringBuffer product= new StringBuffer();
        StringBuffer productId = product.append("0").append(String.valueOf(Integer.valueOf(maxId) + 1));
        return productId.toString();
    }
}
