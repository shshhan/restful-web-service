package com.example.restfulwebservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll(); // h2-console 이하 경로에 모든 권한 접근 허용
        http.csrf().disable();  //cross-site Request Factory 사용안함
        http.headers().frameOptions().disable(); //header에 frame 속성 사용 안함
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("shawn")
                .password("{noop}test1234") //{noop} : 값을 인코딩 없이 바로 사용할 수 있도록 설정
                .roles("USERS");
    }
}
