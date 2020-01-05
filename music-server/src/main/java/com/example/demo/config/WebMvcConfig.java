package com.example.demo.config;

import com.example.demo.interceptor.AccessInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private AccessInterceptor accessInterceptor;
    //跨域允许的方法
    static final String ORIGINS[] = new String[] { "GET", "POST", "PUT", "DELETE" };

    @Override
    //把拦截器Interceptor注册到springboot中
    public void addInterceptors(InterceptorRegistry registry) {
        String interceptPath = "/IP";
        //注册拦截器
        InterceptorRegistration loginIR = registry.addInterceptor(accessInterceptor);
        //配置拦截路径
        loginIR.addPathPatterns(interceptPath);
        //配置不拦截路径
        loginIR.excludePathPatterns("/song/songName").excludePathPatterns("/song/ranking");
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowCredentials(true);
    }
}
