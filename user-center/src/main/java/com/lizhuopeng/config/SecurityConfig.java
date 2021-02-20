package com.lizhuopeng.config;

import com.lizhuopeng.Securityhandler.AuthenticationEntryPointHandler;
import com.lizhuopeng.Securityhandler.AuthenticationFailureHandlerImpl;
import com.lizhuopeng.Securityhandler.AuthenticationSuccessHandlerImpl;
import com.lizhuopeng.Securityhandler.LogoutSuccessHandlerImpl;
import com.lizhuopeng.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService myUserDetailService;


    private final String registerAPI="/user/register";

    private final String loginAPI="/user/login";

    private final String logoutAPI="/user/logout";

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors();//关闭Sc跨域资源共享的保护，还需要甚至过滤器
        httpSecurity.csrf().disable()/*允许跨域*/
                .authorizeRequests()
                // 对注册要允许匿名访问;
                .antMatchers(registerAPI).permitAll()
                //其余请求全部需要登录后访问
                .anyRequest().authenticated()
                //登录Post地址
                .and().formLogin().loginProcessingUrl(loginAPI).permitAll()
                //登录成功后的返回结果
                .successHandler(new AuthenticationSuccessHandlerImpl())
               //登录失败后的返回结果
                .failureHandler(new AuthenticationFailureHandlerImpl())
                //这里配置的logoutUrl为登出接口，并设置可匿名访问
                .and().logout().logoutUrl(logoutAPI).permitAll()
                //登出后的返回结果
                .logoutSuccessHandler(new LogoutSuccessHandlerImpl())
                //配置的为当未登录访问受保护资源时，返回json
                .and().exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPointHandler());

    }

    /**
     * 配置查询数据库的user的service
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService).passwordEncoder(getPasswordEncoder());
    }

    /**
     * 自定义用户认真需要有Bean实现PassWordEncoder接口
     * @return
     */
    @Bean
    protected PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置全局CorsFilter过滤器
     * @return
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
