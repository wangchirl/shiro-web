package com.shadow.shiro.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shadow.shiro.entity.UserRoleEntity;
import com.shadow.shiro.mapper.UserRoleDao;
import com.shadow.shiro.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRoleEntity> implements UserRoleService {



}