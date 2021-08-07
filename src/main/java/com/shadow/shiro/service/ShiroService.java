package com.shadow.shiro.service;

import com.shadow.shiro.entity.UserEntity;
import com.shadow.shiro.entity.UserTokenEntity;

import java.util.Set;

public interface ShiroService {

    /**
     * 权限字符串查询
     */
    Set<String> getUserPermissioms(Long userId);

    /**
     * token查询
     */
    UserTokenEntity getByToken(String token);

    /**
     * 根据用户ID查询用户信息
     */
    UserEntity getByUserId(Long userId);

    /**
     * 根据用户ID查询用户角色
     */
    Set<String> getUserRoles(Long userId);

}
