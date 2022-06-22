package com.horse.yun.auth.filter;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.horse.yun.auth.model.biz.user.JwtUser;
import com.horse.yun.auth.model.biz.user.LoginUser;
import com.horse.yun.auth.util.JwtTokenUtil;
import com.horse.yun.auth.util.ReturnT;
import com.horse.yun.common.web.base.Results;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.horse.yun.auth.constant.Constants.SPLIT_COMMA;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/22 13:05
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private ThreadLocal<Integer> rememberMe = new ThreadLocal<>();

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/v1/cs/auth/login");
    }



    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        // 从输入流中获取到登录的信息
        try {
            LoginUser loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
            rememberMe.set(loginUser.getRememberMe());
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>())
            );
        } catch (IOException e) {
            logger.error("attemptAuthentication error :{}", e);
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        boolean isRemember = rememberMe.get() == 1;

        String role ="";
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            role = authority.getAuthority();
        }
        String token = JwtTokenUtil.createToken(jwtUser.getId(), jwtUser.getUsername(), role, isRemember);
        response.setHeader("token", JwtTokenUtil.TOKEN_PREFIX + token);
        response.setCharacterEncoding("UTF-8");
        Map<String, Object> maps = new HashMap<>();
        maps.put("data", JwtTokenUtil.TOKEN_PREFIX + token);
        maps.put("roles", role.split(SPLIT_COMMA));
        response.getWriter().write(JSONUtil.toJsonStr(Results.success(maps)));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONUtil.toJsonStr(new ReturnT(-1, "Server Error")));
    }
}
