package com.mxnet.service;

import com.mxnet.pojo.User;

public interface UserService {

    User getUserByNameAndPwd(String userName, String pwd);

    int insertUser(User user);
}
