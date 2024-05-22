package com.cc.apigateway.flowLimiter;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/30
 * Time: 20:02
 * Description:
 */

// 网关限流：对某一路由(微服务)进行访问量限流
@Configuration
public class RouteFlowLimiterRule {

    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;

    public RouteFlowLimiterRule(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    // 注入SentinelGatewayFilter实例
    // 初始化一个限流的过滤器
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    // 对每个路由(微服务)的访问量进行限流
    // 配置初始化的限流参数
    @PostConstruct
    public void initGatewayRules() {
        Set<GatewayFlowRule> rules = new HashSet<>();
        // Service-Goods服务路由ID:r-GoodsService
        // 设置1秒最多只能访问2次；测试：1秒访问多次
        rules.add(new GatewayFlowRule("r-GoodsService") // 资源名称,对应routeID
                .setCount(2) // 限流阈值
                .setIntervalSec(1) // 统计时间窗口,单位秒,默认是1秒
        );
        rules.add(new GatewayFlowRule("r-UserService") // 资源名称,对应routeID
                .setCount(2) // 限流阈值
                .setIntervalSec(1) // 统计时间窗口,单位秒,默认是1秒
        );
        rules.add(new GatewayFlowRule("r-ProviderService") // 资源名称,对应routeID
                .setCount(2) // 限流阈值
                .setIntervalSec(1) // 统计时间窗口,单位秒,默认是1秒
        );
        rules.add(new GatewayFlowRule("r-OrderService") // 资源名称,对应routeID
                .setCount(2) // 限流阈值
                .setIntervalSec(1) // 统计时间窗口,单位秒,默认是1秒
        );
        GatewayRuleManager.loadRules(rules);
    }

    // 注入SentinelGatewayBlockExceptionHandler实例
    // 配置限流的异常处理器
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }

    // 自定义限流异常响应页面
    @PostConstruct
    public void initBlockHandlers() {
        BlockRequestHandler blockRequestHandler = new BlockRequestHandler() {
            public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
                Map<String,Object> map = new HashMap<>();
                // 响应码504：网关超时
                map.put("code", 504);
                map.put("message", "服务器繁忙!请稍后重试!");
                // HttpStatus.GATEWAY_TIMEOUT：504，需要和map.put("code", 504)中一致
                return ServerResponse.status(HttpStatus.GATEWAY_TIMEOUT).
                        contentType(MediaType.APPLICATION_JSON_UTF8).
                        body(BodyInserters.fromObject(map));
            }
        };
        GatewayCallbackManager.setBlockHandler(blockRequestHandler);
    }

}
