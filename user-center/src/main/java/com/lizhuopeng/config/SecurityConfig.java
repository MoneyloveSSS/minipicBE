package com.lizhuopeng.config;

import com.lizhuopeng.Securityhandler.AuthenticationEntryPointHandlerImpl;
import com.lizhuopeng.Securityhandler.AuthenticationFailureHandlerImpl;
import com.lizhuopeng.Securityhandler.AuthenticationSuccessHandlerImpl;
import com.lizhuopeng.Securityhandler.LogoutSuccessHandlerImpl;
import com.lizhuopeng.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService myUserDetailService;


    private final String registerAPI="/user/register";

    private final String loginAPI="/user/login";

    private final String logoutAPI="/user/logout";

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors();//关闭Sc跨域资源共享的保护，还需要设置过滤器配置
        httpSecurity.csrf().disable()/*允许跨域*/
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() //允许OPTIONS匿名访问
                .antMatchers("/css/**", "/index").permitAll()

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
                //这里配置的logoutUrl为登出接口
                .and().logout().logoutUrl(logoutAPI)
                //登出后的返回结果
                .logoutSuccessHandler(new LogoutSuccessHandlerImpl())
                //配置的为当未登录访问受保护资源时，返回json
                .and().exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPointHandlerImpl());

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
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:8080");
        config.setAllowCredentials(true);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }
}
