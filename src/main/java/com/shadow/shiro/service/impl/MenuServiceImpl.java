package com.shadow.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shadow.shiro.entity.MenuEntity;
import com.shadow.shiro.mapper.MenuDao;
import com.shadow.shiro.service.MenuService;
import org.springframework.stereotype.Service;

@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao, MenuEntity> implements MenuService {


}