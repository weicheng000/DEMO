package com.example.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static final String SIGN_KEY = "jasonJwtDemo";
    private static final Long EXPIRE = 43200000L;

    /**
     * 生成Jwt令牌
     * @param claims Jwt 第二段 payload 中儲存的內容
     * @return Jwt 字串
     */
    public static String generateJwt(Map<String, Object> claims){
        return Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SIGN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .compact();
    }

    /**
     * 解析Jwt令牌
     * @param jwt 收到的Jwt 字串
     * @return Jwt 第二段 payload 中儲存的內容
     */
    public static Claims parseJWT(String jwt){
        return Jwts.parser()
                .setSigningKey(SIGN_KEY)
                .parseClaimsJws(jwt)
                .getBody();
    }

}
