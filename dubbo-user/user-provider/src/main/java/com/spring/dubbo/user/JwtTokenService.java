package com.spring.dubbo.user;

import com.spring.dubbo.utils.JwtInfo;
import com.spring.dubbo.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/12
 */
@Component
public class JwtTokenService {

    @Value("${jwt.expire}")
    private int expire;

    public String genetatorToken(JwtInfo jwtInfo) {
        return JwtTokenUtil.generatorToken(jwtInfo, expire);
    }

    public JwtInfo getInfoFormToken(String token) {
        return JwtTokenUtil.getTokenInfo(token);
    }

}
