package com.qianjin.jack.config;

import com.qianjin.jack.handler.LoginIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author volume
 * @date 2019/10/18 10:07
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {
    private final LoginIntercept loginIntercept;

    @Autowired
    public AppConfig(LoginIntercept loginIntercept) {
        this.loginIntercept = loginIntercept;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 对接口配置跨域设置
        return new CorsFilter(source);
    }
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 允许任何域名使用
        corsConfiguration.addAllowedHeader("*"); // 允许任何头
        corsConfiguration.addAllowedMethod("*"); // 允许任何方法（post、get等）
        return corsConfiguration;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> nologin = new ArrayList<>();
        nologin.add("/");
        nologin.add("/error");
        nologin.add("/user/login");
        nologin.add("/user/register");
        nologin.add("/product/detail/product");
        nologin.add("/manage/**");
        registry.addInterceptor(loginIntercept).addPathPatterns("/**").excludePathPatterns(nologin);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/manage/**").addResourceLocations("classpath:/static/");
    }
}
