package com.qianjin.jack.handler;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.qianjin.jack.domain.dao.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author volume
 * @date 2019/10/18 10:13
 */
@Component
@Slf4j
public class LoginIntercept implements HandlerInterceptor {
    @Value("${yard}")
    private String yard;

    public static ThreadLocal<UserInfo> userInfoLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("authorization");
        if ((StringUtils.isBlank(authorization) && StringUtils.isBlank(authorization))) {
            response.setStatus(401);
            return false;
        }
        UserInfo userInfo = parseToken(authorization.substring(6));
        userInfoLocal.set(userInfo);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        userInfoLocal.remove();
    }

    private UserInfo parseToken(String authorization) throws Exception {
        UserInfo userInfo = new UserInfo();
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(yard)).build();
        Map<String, Claim> claims = verifier.verify(authorization).getClaims();
        userInfo.setId(claims.get("id").asInt());
        userInfo.setUsername(claims.get("username").asString());
        userInfo.setPhone(claims.get("phone").asString());
        return userInfo;
    }
}
