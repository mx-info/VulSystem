package com.mxnet.service;

import com.mxnet.mapper.UserMapper;
import com.mxnet.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;
    @Override
    public User getUserByNameAndPwd(String userName, String pwd) {
        return userMapper.getUserByNameAndPwd(userName,pwd);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }
}
