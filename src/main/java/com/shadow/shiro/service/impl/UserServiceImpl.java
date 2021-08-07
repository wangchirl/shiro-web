package com.shadow.shiro.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shadow.shiro.entity.UserEntity;
import com.shadow.shiro.mapper.UserDao;
import com.shadow.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<String> queryAllPerms(Long userId) {
        if(userId == null) return new ArrayList<>();
        return userDao.queryAllPerms(userId);
    }

    @Override
    public UserEntity getByUserName(String username) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return getOne(wrapper);
//        return userDao.getByUserName(username);
    }


}