package com.cyl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyl.dao.UserDao;
import com.cyl.domain.User;

/**
 * Created by xuliugen on 2016/5/4.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User getUser(int id) {
        return userDao.getUser(id);
    }
    
    @Transactional
    public boolean addUser(User user) {
        return userDao.addUser(user) == 1 ? true : false;
    }
}
