package com.huawei.serviceimpl;

import com.huawei.entity.UsersDetails;
import com.huawei.entity.UsersMenu;
import com.huawei.productmapper.UserMeunMapper;
import com.huawei.status.BaseController;
import com.huawei.status.Response;
import com.huawei.productmapper.UserMapper;
import com.huawei.service.UserService;
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
import java.util.Optional;


/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserServiceImpl.java
 * @Description TODO
 * @createTime 2021年09月22日 16:25:00
 */
@Service
public class UserServiceImpl extends BaseController implements UserService {


    @Resource
    private UserMapper userMapper;

    @Resource
    private UserMeunMapper userMeunMapper;

    @Override
    public Response selectById(String userId) {
        if(StringUtils.isEmpty(userId)){
            return rtnParmterMiss();
        }
        Optional<UsersDetails> byId = userMapper.findById(Integer.valueOf(userId));
        //List<UsersDetails> users = userMapper.findAll();
        if(byId==null){
            return rtnFail("查询数据为null");
        }
        List<Object> user = new ArrayList<>();
        user.add(byId.get());
        return rtnSuccess(user);
    }

    @Override
    public Response findAllUser() {
        List<UsersMenu> users = userMeunMapper.findAll();
        if(users==null){
            return rtnFail("查询数据为null");
        }

        return rtnSuccess(users);
    }

    /**
     * 条件查询不带分页
     * @param page 当前页
     * @param size 每页显示条数
     * @return
     */
    @Override
    public Response findUserNoCondition(Integer page, Integer size) {
        PageRequest pageRequest = new PageRequest(page,size);
        Page<UsersDetails> all = userMapper.findAll(pageRequest);
        return rtnSuccess(all);
    }

    /**
     * 条件查询带分页
     * @param page 当前页
     * @param size 每页显示的条数
     * @param user 条件对象
     * @return
     */
    @Override
    public Response findUsersCondition(Integer page, Integer size, UsersDetails user) {

        PageRequest pageRequest = new PageRequest(page,size);
        List<UsersDetails> users = userMapper.findAll(new Specification<UsersDetails>() {
            @Override
            public Predicate toPredicate(Root<UsersDetails> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
              //id条件
                if(StringUtils.isNotEmpty(String.valueOf(user.getId()))){
                    predicates.add(criteriaBuilder.equal(root.get("id").as(String.class),user.getId()));
                }
                //姓名
                if(StringUtils.isNoneEmpty(user.getUsername())){
                    predicates.add(criteriaBuilder.equal(root.get("username").as(String.class),user.getUsername()));
                }
               //年龄
                if(StringUtils.isNoneEmpty(String.valueOf(user.getAge()))){
                    predicates.add(criteriaBuilder.equal(root.get("age").as(String.class),user.getAge()));
                }
               ///性别
                if(StringUtils.isNoneEmpty(user.getSex())){
                    predicates.add(criteriaBuilder.equal(root.get("sex").as(String.class),user.getSex()));
                }
                //地址
                if(StringUtils.isNoneEmpty(user.getAddress())){
                    predicates.add(criteriaBuilder.equal(root.get("address").as(String.class),user.getAddress()));
                }
                Predicate[] predicate= new Predicate[predicates.size()];
                return criteriaBuilder.and(predicates.toArray(predicate));
            }
        });
        return rtnSuccess(users);
    }

    /**
     * 添加用户
     * @param name
     * @param age
     * @param sex
     * @return
     */
    @Override
    public Response addUser(String name, String age, String sex) {
        UsersDetails usersDetails = new UsersDetails();
        usersDetails.setUsername(name);
        usersDetails.setAge(Integer.valueOf(age));
        usersDetails.setSex(sex);
        UsersDetails save = userMapper.save(usersDetails);
        if(save==null){
            return rtnFail("查询数据为null");
        }
        return rtnSuccess(1);
    }
}
