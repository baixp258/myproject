package com.huawei;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName WebAPP.java
 * @Description TODO
 * @createTime 2021年09月22日 10:17:00
 */
@SpringBootApplication
@ComponentScan(value = {"com.huawei.*"})
@EnableJpaRepositories(basePackages = {"com.huawei.*"})
@EntityScan(basePackages = {"com.huawei.*"})
@EnableTransactionManagement
public class WebAPP {

    public static void main(String[] args){

        SpringApplication.run(WebAPP.class,args);
    }

}
