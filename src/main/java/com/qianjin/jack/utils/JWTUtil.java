package com.qianjin.jack.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.qianjin.jack.domain.dao.UserInfo;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

public class JWTUtil {

    private static final String yard = "qianjin";

    public static UserInfo parseToken(String authorization) throws Exception {
        UserInfo userInfo = new UserInfo();
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(yard)).build();
        Map<String, Claim> claims = verifier.verify(authorization).getClaims();
        userInfo.setId(claims.get("id").asInt());
        userInfo.setUsername(claims.get("username").asString());
        return userInfo;
    }

    public static String createToken(UserInfo userInfo) throws UnsupportedEncodingException {
        Date issue = new Date();
        Date expires = new Date(issue.getTime() + 24*60*60*1000);
        return JWT.create()
                .withIssuedAt(issue)
                .withExpiresAt(expires)
                .withClaim("id",userInfo.getId())
                .withClaim("username",userInfo.getUsername())
                .sign(Algorithm.HMAC256(yard));
    }
}
