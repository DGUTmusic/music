package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File file=new File("");
        String path=file.getAbsolutePath();
        path.replace("\\","/");
        registry.addResourceHandler("/avatorImages/**").addResourceLocations("file:"+path+"/avatorImages/");
        registry.addResourceHandler("/img/**").addResourceLocations("file:"+path+"/img/");
        registry.addResourceHandler("/song/**").addResourceLocations("file:"+path+"/song/");
    }
}
