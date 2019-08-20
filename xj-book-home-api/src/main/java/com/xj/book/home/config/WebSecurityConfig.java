package com.xj.book.home.config;


import com.xj.book.home.dao.UserDao;
import com.xj.book.home.model.User;
import com.xj.book.home.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        HeadersConfigurer<HttpSecurity> config = http.headers();
        //禁止
        config.cacheControl().disable();
        config.frameOptions().disable();

        //登录权限控制
        config.and().authorizeRequests()
                .antMatchers("/","/login","/add/**").permitAll()
                .anyRequest().authenticated().and().csrf().disable()
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/login")
                .successHandler(new MyAuthenticationSuccessHandler())
                .failureHandler(new MyAuthenticationFailureHandler())
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessHandler(new MyLogoutSuccessHandler())
                .and().exceptionHandling()
                /*.and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(86400)
                .alwaysRemember(true)*/;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(new MyAuthenticationProvider());
    }

    private class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

        @Override
        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write("login success");
        }
    }

    private class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {


        @Override
        public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write("login fail");
        }
    }

    private class MyLogoutSuccessHandler implements LogoutSuccessHandler{

        @Override
        public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write("logout success");
        }
    }

    private class MyAuthenticationProvider implements AuthenticationProvider{

        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            String username = authentication.getName();
            String password = authentication.getCredentials().toString();
            User user = userService.login(username, password);
            if (Objects.isNull(user)){
                throw new InternalAuthenticationServiceException("用户或密码错误");
            }
            if (!user.getActive()) {
                throw new InternalAuthenticationServiceException("账户已停用");
            }
            user = userDao.save(user);
            List<GrantedAuthority> authorityList=AuthorityUtils.createAuthorityList(new String[]{});
            return new UsernamePasswordAuthenticationToken(user, password, authorityList);
        }

        @Override
        public boolean supports(Class<?> aClass) {
            return aClass.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
        }
    }
}
