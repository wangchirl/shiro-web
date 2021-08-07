package com.shadow.shiro.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shadow.shiro.entity.UserEntity;

import java.util.List;

/**
 * 系统用户
 *
 * @author shadow
 * @email shadow@gmail.com
 * @date 2021-08-07 17:05:36
 */
public interface UserService extends IService<UserEntity> {

    List<String> queryAllPerms(Long userId);

    UserEntity getByUserName(String username);



}

