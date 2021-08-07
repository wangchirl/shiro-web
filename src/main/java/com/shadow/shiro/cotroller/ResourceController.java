package com.shadow.shiro.cotroller;

import com.shadow.shiro.entity.MenuEntity;
import com.shadow.shiro.service.MenuService;
import com.shadow.shiro.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
@Slf4j
public class ResourceController {


    @Autowired
    private MenuService menuService;



    @GetMapping("/info")
    @RequiresPermissions("sys:strategy:list")
    public R list() {
        List<MenuEntity> list = menuService.list();
        return R.ok().put("data", list);
    }

    @GetMapping("/info/{menuId}")
    @RequiresPermissions("sys:strategy:info")
    public R info(@PathVariable("menuId") long menuId) {
        MenuEntity entity = menuService.getById(menuId);
        return R.ok().put("data", entity);
    }

    @GetMapping("/add")
    @RequiresRoles("role2")
    public R add() {
        log.info("添加接口调用");
        return R.ok().put("data","添加成功");
    }

}
