package com.shadow.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shadow.shiro.entity.UserTokenEntity;
import com.shadow.shiro.mapper.UserTokenDao;
import com.shadow.shiro.service.UserTokenService;
import com.shadow.shiro.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("userTokenService")
public class UserTokenServiceImpl extends ServiceImpl<UserTokenDao, UserTokenEntity> implements UserTokenService {

    @Autowired
    private UserTokenDao userTokenDao;

    @Override
    public String createToken(Long userId) {
        // 1、jwt 生成token
        String token = JWTUtils.generateToken(userId + "");
        // 2、保存到数据库
        long expire = JWTUtils.getEXPIRE();
        // 当前时间
        Date now = new Date();
        // 过期时间
        Date expireDate = new Date(now.getTime() + expire);
        QueryWrapper<UserTokenEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        UserTokenEntity tokenEntity = this.getOne(wrapper);
        // 新增 or 更新
        if (tokenEntity == null) {
            tokenEntity = new UserTokenEntity();
            tokenEntity.setToken(token)
                    .setUserId(userId)
                    .setExpireTime(expireDate)
                    .setUpdateTime(now);
            this.save(tokenEntity);
        } else {
            tokenEntity.setToken(token)
                    .setUserId(userId)
                    .setExpireTime(expireDate)
                    .setUpdateTime(now);
            QueryWrapper<UserTokenEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userId);
            this.update(tokenEntity, queryWrapper);
        }
        return token;
    }

    @Override
    public void deleteByToken(String token) {
        QueryWrapper<UserTokenEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("token", token);
        userTokenDao.delete(wrapper);
    }
}