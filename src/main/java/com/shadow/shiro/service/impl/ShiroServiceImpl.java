package com.shadow.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shadow.shiro.entity.UserEntity;
import com.shadow.shiro.entity.UserTokenEntity;
import com.shadow.shiro.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private RoleService roleService;

    @Override
    public Set<String> getUserPermissioms(Long userId) {
        // 当前用户 + 用户角色 + 角色菜单 + 菜单权限
        List<String> list =  userService.queryAllPerms(userId);
        // 字符串分割
        HashSet<String> perms = new HashSet<>();
        list.forEach(item -> {
            if(StringUtils.isNoneBlank(item)) {
                perms.addAll(Arrays.asList(item.split(",")));
            }
        });
        return perms;
    }

    @Override
    public UserTokenEntity getByToken(String token) {
        QueryWrapper<UserTokenEntity> wrapper = new QueryWrapper<UserTokenEntity>().eq("token", token);
        return userTokenService.getOne(wrapper);
    }

    @Override
    public UserEntity getByUserId(Long userId) {
        return userService.getById(userId);
    }

    @Override
    public Set<String> getUserRoles(Long userId) {
        // 当前用户 + 用户角色 + 角色名称
        List<String> list = roleService.queryAllRoles(userId);
        HashSet<String> roles = new HashSet<>();
        list.forEach(item -> {
            if(StringUtils.isNoneBlank(item)) {
                roles.add(item);
            }
        });
        return roles;
    }
}
