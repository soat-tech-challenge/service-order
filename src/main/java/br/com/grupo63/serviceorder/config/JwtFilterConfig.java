package br.com.grupo63.serviceorder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class JwtFilterConfig {

    @Autowired
    private JwtService jwtService;

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterFilterRegistrationBean() {
        FilterRegistrationBean<JwtFilter> jwtFilterFilterRegistrationBean = new FilterRegistrationBean<>();
        jwtFilterFilterRegistrationBean.setFilter(new JwtFilter(jwtService));
        jwtFilterFilterRegistrationBean.setUrlPatterns(List.of("/orders"));
        return jwtFilterFilterRegistrationBean;
    }
}
