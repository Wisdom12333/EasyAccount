package com.shirj.pub.utils;

import com.shirj.api.entity.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.UUID;

/**
 * @author shirj, wisdom12333@iCloud.com
 */
@Slf4j
public class TokenUtil {

    public static final long EXPIRE_TIME = 1000*60*30;
    public static final String TOKEN_SECRET = "token";
    public static final String SUBJECT = "easyAccount";

    public static String createToken(User user) {


            JwtBuilder jwtBuilder = Jwts.builder();
            return jwtBuilder
                    //header
                    .setHeaderParam("typ", "JWT")
                    .setHeaderParam("alg", "HS256")
                    //payload
                    .claim("username", user.getUserName())
                    .claim("userId", user.getUserId())
                    .setSubject(SUBJECT)
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                    .setId(UUID.randomUUID().toString())
                    //signature
                    .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET)
                    .compact();

    }

    public static boolean verify(String token) {

        JwtParser jwtParser = Jwts.parser();
        try{
            Jws<Claims> claimsJws = jwtParser.setSigningKey(TOKEN_SECRET).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            String claimsSubject = claims.getSubject();
            return SUBJECT.equals(claimsSubject);
        }catch (Exception e){
            return false;
        }
    }
}
