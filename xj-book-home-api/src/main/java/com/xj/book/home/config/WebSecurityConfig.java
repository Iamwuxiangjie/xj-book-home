package com.xj.book.home.config;


import com.xj.book.home.dao.UserDao;
import com.xj.book.home.model.User;
import com.xj.book.home.service.BaseService;
import com.xj.book.home.service.UserService;
import com.xj.book.home.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Collection;
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

    @Autowired
    private DataSource dataSource;

    @Autowired
    private BaseService baseService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        HeadersConfigurer<HttpSecurity> config = http.headers();
        //禁止
        config.cacheControl().disable();
        config.frameOptions().disable();

        //登录权限控制
        config.and().authorizeRequests()
                .antMatchers("/","/login","/test/**").permitAll()
                .antMatchers("/web/admin/**").hasRole("admin")
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
                .accessDeniedHandler(new MyAccessDeniedHandler())
                .and()
                .rememberMe()
                .tokenRepository(getPersistentTokenRepository())
                .tokenValiditySeconds(86400)
                .alwaysRemember(true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(new MyAuthenticationProvider());
        try {
            auth.userDetailsService(new MyUserDetailsService());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PersistentTokenRepository getPersistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        return tokenRepositoryImpl;
    }

    private class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

        @Override
        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            User user= (User) authentication.getPrincipal();
            httpServletResponse.getWriter().write(MapperUtils.originalSuccess(user));
        }
    }

    private class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {


        @Override
        public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(MapperUtils.originalFail("用户名或密码错误"));
        }
    }

    private class MyLogoutSuccessHandler implements LogoutSuccessHandler{

        @Override
        public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(MapperUtils.originalSuccess("退出成功"));
        }
    }

    private class MyAuthenticationProvider implements AuthenticationProvider{

        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            String username = authentication.getName();
            String password = authentication.getCredentials().toString();
            User user = userService.login(username, password);
            if (Objects.isNull(user)){
                throw new InternalAuthenticationServiceException("用户名或密码错误");
            }
            if (!user.getActive()) {
                throw new InternalAuthenticationServiceException("账户已停用");
            }
            return new UsernamePasswordAuthenticationToken(user, password, new MyUserDetails(user).getAuthorities());
        }

        @Override
        public boolean supports(Class<?> aClass) {
            return aClass.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
        }
    }

    private class MyUserDetailsService implements UserDetailsService {

        @Override
        public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
            User user =userDao.findFirstByPhone(s);
            if(Objects.isNull(user)){
                throw new UsernameNotFoundException("用户名不存在");
            }
            return new MyUserDetails(user);
        }
    }

    private class MyUserDetails implements UserDetails{

        private User user;

        public MyUserDetails(User user){
            this.user=user;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<String> roles = baseService.listByUserId(this.user.getId());
            String[] authoritys = roles.toArray(new String[]{});
            List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(authoritys);
            return authorityList;
        }

        @Override
        public String getPassword() {
            return this.user.getPassword();
        }

        @Override
        public String getUsername() {
            return this.user.getPhone();
        }

        @Override
        public boolean isAccountNonExpired() {
            return this.user.getActive();
        }

        @Override
        public boolean isAccountNonLocked() {
            return this.user.getActive();
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return this.user.getActive();
        }

        @Override
        public boolean isEnabled() {
            return this.user.getActive();
        }
    }

    private class MyAccessDeniedHandler implements AccessDeniedHandler{

        @Override
        public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(MapperUtils.originalDenied("您无权访问"));
        }
    }

}
