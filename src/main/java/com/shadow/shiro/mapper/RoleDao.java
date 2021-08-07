package com.shadow.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shadow.shiro.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色
 *
 * @author shadow
 * @email shadow@gmail.com
 * @date 2021-08-07 17:05:36
 */
@Mapper
public interface RoleDao extends BaseMapper<RoleEntity> {

    List<String> queryAllRoles(@Param("userId") Long userId);

}
