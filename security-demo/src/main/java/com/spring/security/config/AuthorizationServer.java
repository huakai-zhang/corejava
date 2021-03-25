package com.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 如果要使用内存方式 clients.inMemory()
        clients.inMemory()
                .withClient("c1")
                .secret("$2a$10$LABRBUOdPFaYMwyAKSrlXuqbH8Dh4ruFM2js8z3CCh2ZUZ8BKCd/C")
                .scopes("ROLE_ADMIN", "ROLE_USER", "ROLE_API")
                .authorizedGrantTypes("password", "authorization_code");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // oauth/token_key公开
        security.tokenKeyAccess("permitAll()")
                // oauth/check_token公开
                .checkTokenAccess("permitAll()");//.allowFormAuthenticationForClients();
    }

    /**
     * 令牌访问端点
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 密码模式需要
        endpoints.authenticationManager(authenticationManager);
    }

}
