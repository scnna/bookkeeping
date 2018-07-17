package com.bookkeep.service;

import com.bookkeep.domain.User;
import com.bookkeep.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yht on 2018/3/10.
 */
@Service
public class LoginService {
    @Autowired
    UserMapper userMapper;

    public User checkUser(User user) {
        return userMapper.selectByUser(user);
    }
}
