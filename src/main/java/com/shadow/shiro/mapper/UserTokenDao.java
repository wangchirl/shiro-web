package com.shadow.shiro.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shadow.shiro.entity.UserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token
 *
 * @author shadow
 * @email shadow@gmail.com
 * @date 2021-08-07 17:05:36
 */
@Mapper
public interface UserTokenDao extends BaseMapper<UserTokenEntity> {

}
