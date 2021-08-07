package com.shadow.shiro.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shadow.shiro.entity.RoleMenuEntity;
import com.shadow.shiro.mapper.RoleMenuDao;
import com.shadow.shiro.service.RoleMenuService;
import org.springframework.stereotype.Service;

@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenuEntity> implements RoleMenuService {



}