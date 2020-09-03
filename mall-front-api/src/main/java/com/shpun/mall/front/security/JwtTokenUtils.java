package com.shpun.mall.front.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

/**
 * @Description: JWT工具类
 * @Author: sun
 * @Date: 2020/8/23 20:08
 */
public class JwtTokenUtils {

    private JwtTokenUtils(){
    }

    /**
     * Base64加密后的字符串，即私钥
     */
    public static final String SECRET_KEY = "shpun";

    /**
     * token认证的类型
     */
    public static final String TOKEN_TYPE = "JWT";

    /**
     * token认证的头部名
     */
    public static final String TOKEN_HEADER = "Authorization";

    /**
     * token认证的前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * token的超时时间，这里是24小时
     */
    public static final long EXPIRY_TIME = 24 * 60 * 60L;

    /**
     * Issuer
     */
    public static final String ISSUER = "mall";

    /**
     * authority claims
     */
    public static final String AUTHORITY_CLAIMS = "au";

    /**
     * 创建JwtToken
     * @param username
     * @return
     */
    public static String createJwtToken(String username, List<String> authorities) {
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + EXPIRY_TIME * 1000);

        String jwtToken = Jwts.builder()
                .setHeaderParam("type", TOKEN_TYPE)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .claim(AUTHORITY_CLAIMS, String.join(",", authorities))
                .setIssuer(ISSUER)
                .setIssuedAt(createdDate)
                .setSubject(username)
                .setExpiration(expirationDate)
                .compact();
        return TOKEN_PREFIX + jwtToken;
    }

    /**
     * 判断token是否过期
     * @param token
     * @return
     */
    public static boolean isTokenExpired(String token) {
        Date expiredDate = getTokenBody(token).getExpiration();
        return expiredDate.before(new Date());
    }

    /**
     * 获取token中的username
     * @param token
     * @return
     */
    public static String getUsernameByToken(String token) {
        return getTokenBody(token).getSubject();
    }

    /**
     * 获取tokenBody
     * @param token
     * @return
     */
    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 验证token是否还有效
     * @param token
     * @param userDetails
     * @return
     */
    public static boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameByToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

}
