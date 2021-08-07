package com.shadow.shiro.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shadow.shiro.entity.MenuEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单管理
 *
 * @author shadow
 * @email shadow@gmail.com
 * @date 2021-08-07 17:05:36
 */
@Mapper
public interface MenuDao extends BaseMapper<MenuEntity> {

}
