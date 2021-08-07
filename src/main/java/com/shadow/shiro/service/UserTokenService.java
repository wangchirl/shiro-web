package com.shadow.shiro.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shadow.shiro.entity.UserTokenEntity;

/**
 * 系统用户Token
 *
 * @author shadow
 * @email shadow@gmail.com
 * @date 2021-08-07 17:05:36
 */
public interface UserTokenService extends IService<UserTokenEntity> {

    String createToken(Long userId);

    void deleteByToken(String token);

}

