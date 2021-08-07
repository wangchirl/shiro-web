package com.shadow.shiro.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shadow.shiro.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统用户
 *
 * @author shadow
 * @email shadow@gmail.com
 * @date 2021-08-07 17:05:36
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

    List<String> queryAllPerms(@Param("userId") Long userId);

    UserEntity getByUserName(@Param("username") String username);

}
