package com.example.demo.configuration;

import com.example.demo.interceptor.ServiceCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final ServiceCheckInterceptor serviceCheckInterceptor;

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(serviceCheckInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login");
    }

}
