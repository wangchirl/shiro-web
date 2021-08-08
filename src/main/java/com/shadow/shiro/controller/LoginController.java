package com.shadow.shiro.controller;

import com.google.code.kaptcha.Producer;
import com.shadow.shiro.constants.CommonConstants;
import com.shadow.shiro.vo.R;
import com.shadow.shiro.dto.LoginUserDTO;
import com.shadow.shiro.entity.UserEntity;
import com.shadow.shiro.exception.ServiceException;
import com.shadow.shiro.service.UserService;
import com.shadow.shiro.service.UserTokenService;
import com.shadow.shiro.shiro.CustomerToken;
import com.shadow.shiro.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private Producer producer;

    /**
     * 登录
     */
    @PostMapping("/login")
    public R login(HttpServletRequest request, @Valid @RequestBody LoginUserDTO loginUserDTO) {
        // 0、校验验证码
        if(!loginUserDTO.getCaptcha().equalsIgnoreCase(request.getSession().getAttribute(CommonConstants.CAPTCHA).toString())) {
            throw new ServiceException("验证码错误", 400);
        }
        // 1、用户名查询用户
        UserEntity userEntity = userService.getByUserName(loginUserDTO.getUsername());
        if(userEntity == null) {
            throw new ServiceException("用户不存在", 404);
        }
        // 2、校验密码
        String salt = userEntity.getSalt();
        String pwd = new Md5Hash(loginUserDTO.getPassword(), salt, 1024).toHex();
        if(!pwd.equals(userEntity.getPassword())) {
            throw new ServiceException("密码不正确", 400);
        }
        // 3、用户状态确认
        if(userEntity.getStatus() == 0) {
            throw new ServiceException("用户已被禁用");
        }
        // 4、生成token jwt + save
        String token = userTokenService.createToken(userEntity.getUserId());
        // 5、shiro 登录
        SecurityUtils.getSubject().login(new CustomerToken(token));
        return R.ok("success").put("token", token);
    }

    /**
     * 登出
     */
    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        // 1、请求中获取token 参数
        String token = request.getHeader(JWTUtils.getAUTHORIZATION());
        if(StringUtils.isEmpty(token)) {
            token = request.getParameter(JWTUtils.getAUTHORIZATION());
        }
        // 2、删除token，shiro登出
        if(!StringUtils.isEmpty(token)) {
            userTokenService.deleteByToken(token);
            SecurityUtils.getSubject().logout();
        }
    }

    /**
     * 验证码生成
     */
    @GetMapping("/captcha")
    public void captcha(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String code = producer.createText();
        BufferedImage image = producer.createImage(code);
        req.getSession().setAttribute(CommonConstants.CAPTCHA, code);
        ImageIO.write(image, "jpg", res.getOutputStream());
        log.info("captcha code {}", code);
    }

}
