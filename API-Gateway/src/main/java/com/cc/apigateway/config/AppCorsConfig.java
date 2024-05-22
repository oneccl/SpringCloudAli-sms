package com.cc.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/9
 * Time: 13:43
 * Description:
 */

// Gateway CORS跨域配置
// 通过Java配置类配置

@Configuration
public class AppCorsConfig {

    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 开发环境设置为*，生产环境设置为域名
        // 域名：域名地址，数据传输时对计算机的定位标识
        // 由于IP地址不方便记忆且不能显示地址组织的名称和性质等缺点，则使用DNS将域名与IP地址相互映射
        // 例如：百度的域名为：baidu.com
        config.addAllowedMethod("*");
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(source);
    }

}
