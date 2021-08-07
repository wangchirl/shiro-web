package com.shadow.shiro.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shadow.shiro.entity.RoleEntity;

import java.util.List;

/**
 * 角色
 *
 * @author shadow
 * @email shadow@gmail.com
 * @date 2021-08-07 17:05:36
 */
public interface RoleService extends IService<RoleEntity> {

    List<String> queryAllRoles(Long userId);

}

