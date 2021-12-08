package com.huawei.productmapper;

import com.huawei.entity.UsersDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface UserMapper extends JpaRepository<UsersDetails,Integer> ,JpaSpecificationExecutor<UsersDetails> {


}
