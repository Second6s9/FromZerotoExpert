package com.seconds.config;

import com.seconds.interceptor.IpInterceptor;
import com.seconds.interceptor.LoginInterceptor;
import com.seconds.interceptor.PvInterceptor;
import com.seconds.interceptor.UvInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@Configuration
public class MySpringMvcConfig implements WebMvcConfigurer {

    @Autowired
    private PvInterceptor pvInterceptor;

    @Autowired
    private UvInterceptor uvInterceptor;

    @Autowired
    private IpInterceptor ipInterceptor;

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/static/css/**")
                .addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/static/js/**")
                .addResourceLocations("classpath:/static/js/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(pvInterceptor).addPathPatterns("/**").excludePathPatterns(
                "/FromZerotoExpert/webInformation/**",
                "/FromZerotoExpert/redirect_login",
                "/FromZerotoExpert/main/updateOnlineTime",
                "/FromZerotoExpert/main/getOnlineNums",
                "/FromZerotoExpert/main/quit",
                "/FromZerotoExpert/login/user",
                "/FromZerotoExpert/checkUsername",
                "/FromZerotoExpert/checkPassword",
                "/FromZerotoExpert/checkEmail",
                "/FromZerotoExpert/checkAll",
                "/FromZerotoExpert",
                "/FromZerotoExpert/redirect_login",
                "/redirect_login",
                "/FromZerotoExpert/save",
                "/redirect_login",
                "/**/*.html",                //html静态资源
                "/**/*.js",                  //js静态资源
                "/**/*.css"                  //css静态资源
        );

        registry.addInterceptor(uvInterceptor).addPathPatterns("/**").excludePathPatterns(
                "/FromZerotoExpert/webInformation/**",
                "/FromZerotoExpert/redirect_login",
                "/FromZerotoExpert/main/updateOnlineTime",
                "/FromZerotoExpert/main/getOnlineNums",
                "/FromZerotoExpert/main/quit",
                "/FromZerotoExpert/login/user",
                "/FromZerotoExpert/checkUsername",
                "/FromZerotoExpert/checkPassword",
                "/FromZerotoExpert/checkEmail",
                "/FromZerotoExpert/checkAll",
                "/FromZerotoExpert",
                "/FromZerotoExpert/redirect_login",
                "/redirect_login",
                "/FromZerotoExpert/save",
                "/redirect_login",
                "/**/*.html",                //html静态资源
                "/**/*.js",                  //js静态资源
                "/**/*.css"                  //css静态资源
        );

        registry.addInterceptor(ipInterceptor).addPathPatterns("/**").excludePathPatterns(
                "/FromZerotoExpert/webInformation/**",
                "/FromZerotoExpert/redirect_login",
                "/FromZerotoExpert/main/updateOnlineTime",
                "/FromZerotoExpert/main/getOnlineNums",
                "/FromZerotoExpert/main/quit",
                "/FromZerotoExpert/login/user",
                "/FromZerotoExpert/checkUsername",
                "/FromZerotoExpert/checkPassword",
                "/FromZerotoExpert/checkEmail",
                "/FromZerotoExpert/checkAll",
                "/FromZerotoExpert",
                "/FromZerotoExpert/redirect_login",
                "/redirect_login",
                "/FromZerotoExpert/save",
                "/redirect_login",
                "/**/*.html",                //html静态资源
                "/**/*.js",                  //js静态资源
                "/**/*.css"                  //css静态资源
        );



        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(
                "/redirect_login",
                "/FromZerotoExpert/redirect_login",
                "/FromZerotoExpert/login/**",
                "/FromZerotoExpert/register",
                "/FromZerotoExpert/checkUsername",
                "/FromZerotoExpert/checkPassword",
                "/FromZerotoExpert/checkEmail",
                "/FromZerotoExpert/checkAll",
                "/FromZerotoExpert/save",
                "/**/*.html",                //html静态资源
                "/**/*.js",                  //js静态资源
                "/**/*.css"                  //css静态资源
        );


    }
}
