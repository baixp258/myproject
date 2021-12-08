package com.huawei.config;


import com.huawei.utils.BaseCanstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName WebMvcConfigurer.java
 * @Description TODO
 * @createTime 2021年10月30日 23:27:00
 */

@SuppressWarnings("ALL")
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //  /home/file/**为前端URL访问路径  后面 file:xxxx为本地磁盘映射
        registry.addResourceHandler(BaseCanstants.REQPICTUREPARTEN).addResourceLocations(BaseCanstants.BASEPROJECTMANYPICTUREPATH);
    }
}
