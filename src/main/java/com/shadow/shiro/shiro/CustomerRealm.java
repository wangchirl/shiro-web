package com.shadow.shiro.shiro;

import com.shadow.shiro.entity.UserEntity;
import com.shadow.shiro.entity.UserTokenEntity;
import com.shadow.shiro.exception.ServiceException;
import com.shadow.shiro.service.ShiroService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    private ShiroService shiroService;

    // 指定自定义 token
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof CustomerToken;
    }

    // ② 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 1、拿到主身份信息，SimpleAuthenticationInfo(userEntity.getUserId(), accessToken, getName()); 设置的用户id
        Long userId = (Long) principalCollection.getPrimaryPrincipal();
        log.info("查询授权信息：{}", userId);
        // 2、根据用户id 查询权限字符串
        Set<String> permissioms = shiroService.getUserPermissioms(userId);
        // 3、根据用户id 查询角色字符串
        Set<String> roles = shiroService.getUserRoles(userId);
        // 4、构建权限信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(permissioms);
        authorizationInfo.addRoles(roles);
        return authorizationInfo;
    }

    // ① 身份认证
    // 请求先来到这里，后续授权回到 doGetAuthorizationInfo
    // OAuth2 身份验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1、token 存储JWT token 信息
        String accessToken = (String) token.getPrincipal();
        // 2、根据token 查询用户ID
        UserTokenEntity tokenEntity = shiroService.getByToken(accessToken);
        // 3、校验
        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
            throw new ServiceException("token 已失效，请重新登录");
        }
        UserEntity userEntity = shiroService.getByUserId(tokenEntity.getUserId());
        if(userEntity == null) {
            throw new UnknownAccountException("用户不存在");
        }
        if(userEntity.getStatus() == 0) {
            throw new DisabledAccountException("用户被禁用，请联系管理员");
        }
        // 4、构建身份信息
        return new SimpleAuthenticationInfo(userEntity.getUserId(), accessToken, getName());
    }
}
