package com.shirj.svc.components;

import com.shirj.api.entity.User;
import com.shirj.pub.utils.TimeUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * Utils for Token.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Slf4j
public final class TokenUtils {

    public static final long EXPIRE_TIME = 1000*60*60;
    public static final String TOKEN_SECRET = "EASY_ACCOUNT";
    public static final String SUBJECT = "easyAccount";

    /**
     * Create a token by user info.
     *
     * @param user the user
     * @return a token
     */
    public static String createToken(@NonNull User user) {


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

    /**
     * Verify the token.
     *
     * @param token the token
     * @return true if the token is valid
     */
    public static boolean verify(String token) {

        String realToken = token.split(" ")[1];
        try{
            Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(realToken);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Check whether the token can be refreshed.
     *
     * @param token the token
     * @return true if the token can be refreshed
     */
    public static Boolean checkRefresh(String token) {

        try {
            final String realToken = token.split(" ")[1];
            Claims claims = Jwts.parser()
                    .setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(realToken)
                    .getBody();
            //final Date iat = claims.getIssuedAt(); 获取签发时间
            final LocalDateTime exp = TimeUtils.date2LocalDateTime(claims.getExpiration());
            return exp.plusMinutes(-20).isBefore(LocalDateTime.now());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Return a new token.
     *
     * @param token old token
     * @return new token
     */
    public static String refreshToken(String token) {

        try {
            final String realToken = token.split(" ")[1];
            final Claims claims = Jwts.parser()
                    .setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(realToken)
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
