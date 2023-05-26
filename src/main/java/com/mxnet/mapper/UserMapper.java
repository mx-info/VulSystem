package com.mxnet.mapper;

import com.mxnet.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    //根据name 和 pwd查询用户是否存在
    User getUserByNameAndPwd(String userName, String pwd);

    //新增以为用户
    int insertUser(User user);
}
