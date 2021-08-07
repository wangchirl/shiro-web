package com.shadow.shiro.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shadow.shiro.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户与角色对应关系
 *
 * @author shadow
 * @email shadow@gmail.com
 * @date 2021-08-07 17:05:36
 */
@Mapper
public interface UserRoleDao extends BaseMapper<UserRoleEntity> {

}
