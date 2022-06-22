package com.horse.yun.auth.filter;

import com.alibaba.fastjson.JSON;
import com.horse.yun.auth.util.JwtTokenUtil;
import com.horse.yun.common.util.UserContext;
import com.horse.yun.common.web.base.Results;
import com.horse.yun.common.web.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/22 13:13
 */
@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String tokenHeader = request.getHeader(JwtTokenUtil.TOKEN_HEADER);
        // 如果请求头中没有 Authorization 信息则直接放行
        if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        // 如果请求头中有 Token, 则进行解析, 并且设置认证信息
        try {
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
        } catch (Exception ex) {
            // 返回 Json 形式的错误信息
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(JSON.toJSONString(Results.failure("-1", ex.getMessage())));
            response.getWriter().flush();
            return;
        }

        super.doFilterInternal(request, response, chain);
    }
    /**
     * Token 中获取用户信息并新建一个 Token.
     *
     * @param tokenHeader
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        String token = tokenHeader.replace(JwtTokenUtil.TOKEN_PREFIX, "");
        boolean expiration = JwtTokenUtil.isExpiration(token);
        if (expiration) {
            throw new ServiceException("登录时间过长，请退出重新登录");
        }

        String username = JwtTokenUtil.getUsername(token);
        UserContext.setUserName(username);

        String role = JwtTokenUtil.getUserRole(token);
        if (username != null) {
            return new UsernamePasswordAuthenticationToken(username, null,
                    Collections.singleton(new SimpleGrantedAuthority(role))
            );
        }

        return null;
    }
}
