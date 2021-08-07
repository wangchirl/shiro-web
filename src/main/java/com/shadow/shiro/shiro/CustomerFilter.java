package com.shadow.shiro.shiro;

import com.alibaba.fastjson.JSON;
import com.shadow.shiro.vo.R;
import com.shadow.shiro.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomerFilter extends AuthenticatingFilter {

    // ②
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String token = getRequestToken((HttpServletRequest) servletRequest);
        if(StringUtils.isEmpty(token)) {
            log.warn("token is not exist");
            return null;
        }
        return new CustomerToken(token);
    }

    // 全部请求转发给 onAccessDenied
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return ((HttpServletRequest)request).getMethod().equals(RequestMethod.OPTIONS.name());
    }

    // ① 请求先到这里，执行 executeLogin 回到 createToken
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        // 1、检查请求携带的 token
        String token = getRequestToken((HttpServletRequest) servletRequest);
        if(StringUtils.isEmpty(token)) {
            // 响应错误信息
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Origin", ((HttpServletRequest)servletRequest).getHeader("Origin"));
            R r = R.error(HttpStatus.SC_UNAUTHORIZED, "invalid token");
            response.getWriter().print(JSON.toJSONString(r));
            return false;
        }
        // 2、正常情况
        return executeLogin(servletRequest, servletResponse);
    }

    // 失败情况下的响应
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", ((HttpServletRequest)request).getHeader("Origin"));
        try {
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            R r = R.error(HttpStatus.SC_UNAUTHORIZED, throwable.getMessage());
            response.getWriter().print(JSON.toJSONString(r));
        } catch (IOException ex) {
            log.error("login failed ... ", ex.getMessage(), ex);
        }
        return false;
    }

    private String getRequestToken(HttpServletRequest request) {
        String token = request.getHeader(JWTUtils.getAUTHORIZATION());
        if(StringUtils.isEmpty(token)) {
            token = request.getParameter(JWTUtils.getAUTHORIZATION());
        }
        return token;
    }
}
