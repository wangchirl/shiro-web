package com.shadow.shiro.utils;


import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JWTUtils {

    /**
     * HS256 bit  长度要>= 256 字节长度>=32
     * HS384 bit  长度要>= 256 字节长度>=48
     * HS512 bit  长度要>= 256 字节长度>=64
     */
    private static final String AUTHORIZATION = "Authorization"; // 前端 header 携带的key
    private static final String TOKEN_MAGIC = "Barre "; // token 魔数
    private static final String SECRET_KEY = "ioalswJxadawadsa*221^@!xwadsaawa"; // 秘钥
    private static final long EXPIRE = 60 * 60 * 1000; // 过期时间
    private static final String ISSUER = "issuer@shadow"; //签发人
    private static final String USER_ID = "userId";


    /**
     * 生成 token
     */
    public static String generateToken(String userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claims.ISSUER, ISSUER);
        claims.put(USER_ID, userId);
        return generateToken(claims);
    }

    private static String generateToken(Map<String, Object> claims) {
        Date now = new Date();
        // 过期时间
        Date expire = new Date(now.getTime() + EXPIRE);
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // {"typ":"JWT","alg":"HS256"}
                .setHeaderParam(JwsHeader.ALGORITHM, SignatureAlgorithm.HS256)
                .setClaims(claims) // poyload信息
                .setIssuedAt(now) // 签发时间
                .setExpiration(expire) // 过期时间
                .signWith(SignatureAlgorithm.HS256, generateKey()) // 签名
                .compact();
    }

    private static Key generateKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }


    public static String getAUTHORIZATION() {
        return AUTHORIZATION;
    }

    public static long getEXPIRE() {
        return EXPIRE;
    }
}
