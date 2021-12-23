package com.shirj.pub.utils;

import com.shirj.api.entity.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * @author shirj, wisdom12333@iCloud.com
 */
@Slf4j
public class TokenUtils {

    public static final long EXPIRE_TIME = 1000*60*60;
    public static final String TOKEN_SECRET = "EASY_ACCOUNT";
    public static final String SUBJECT = "easyAccount";

    public static String createToken(User user) {


            JwtBuilder jwtBuilder = Jwts.builder();
            return jwtBuilder
                    //header
                    .setHeaderParam("typ", "JWT")
                    .setHeaderParam("alg", "HS256")
                    //payload
                    .claim("username", user.getUsername())
                    .claim("userId", user.getUserId())
                    .setSubject(SUBJECT)
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                    .setId(UUID.randomUUID().toString())
                    //signature
                    .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET)
                    .compact();

    }

    public static boolean verify(String token) {

        try{
            Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static Boolean checkRefresh(String token) {

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            //final Date iat = claims.getIssuedAt(); 获取签发时间
            final LocalDateTime exp = TimeUtils.date2LocalDateTime(claims.getExpiration());
            return exp.plusMinutes(-20).isBefore(LocalDateTime.now());
        } catch (Exception e) {
            return false;
        }
    }

    public static String refreshToken(String token) {

        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            User user = User.builder()
                    .username(String.valueOf(claims.get("username")))
                    .password(String.valueOf(claims.get("password")))
                    .build();
            return createToken(user);
        } catch (Exception e) {
            return "";
        }

    }

}
