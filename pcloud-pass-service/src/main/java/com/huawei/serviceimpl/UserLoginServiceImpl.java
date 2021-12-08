package com.huawei.serviceimpl;

import com.huawei.entity.LoginUser;
import com.huawei.productmapper.UserLoginMapper;
import com.huawei.service.UserLoginService;
import com.huawei.status.BaseController;
import com.huawei.status.Response;
import com.huawei.utils.DateUtils;
import com.huawei.utils.RedisUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
@Service
public class UserLoginServiceImpl extends BaseController implements UserLoginService {


    @Resource
    private UserLoginMapper userLoginMapper;



    @Override
    public Response login(String username, String password) {
        //查询登录的用户信息
        List<Map<String, Object>> loginUser = userLoginMapper.findLoginUser(username, password);
        if (loginUser==null || loginUser.size()==0){
            return rtnFail("登录失败，不在改用户");
        }
        List<String> urls = new ArrayList<>();
        //获取该登录用户的权限信息
        for (int i=0;i<loginUser.size();i++){
            Map<String, Object> userMap = loginUser.get(i);
            if(userMap.keySet().contains("permission_url")){
                urls.add((String)userMap.get("permission_url"));
            }
        }
        //封装相应user对象
        LoginUser user = getLoginUser(loginUser, urls);
        //获取没当前访问量
        RedisUtils.getJedis().getResource().incrBy(DateUtils.getCurrentTime(),1);
        //获取历史访问量
        RedisUtils.getJedis().getResource().incrBy("visit_total",1);
        return rtnSuccess(user);
    }

    private LoginUser getLoginUser(List<Map<String, Object>> loginUser, List<String> urls) {
        LoginUser user = new LoginUser();
        user.setUserid((Integer)loginUser.get(0).get("userid"));
        user.setUsername((String)loginUser.get(0).get("username"));
        user.setAddress((String)loginUser.get(0).get("address"));
        user.setEmail((String)loginUser.get(0).get("email"));
        user.setCreatetime((String)loginUser.get(0).get("createtime"));
        user.setRoleid((Integer)loginUser.get(0).get("roleid"));
        user.setRole_name((String)loginUser.get(0).get("role_name"));
        user.setCreate_time((String)loginUser.get(0).get("create_time"));
        user.setAuth_name((String)loginUser.get(0).get("auth_name"));
        user.setMenu(urls);
        return user;
    }


}
