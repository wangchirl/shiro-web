package com.shadow.shiro.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shadow.shiro.entity.RoleEntity;
import com.shadow.shiro.mapper.RoleDao;
import com.shadow.shiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<String> queryAllRoles(Long userId) {
        if(userId == null) return new ArrayList<>();
        return roleDao.queryAllRoles(userId);
    }
}