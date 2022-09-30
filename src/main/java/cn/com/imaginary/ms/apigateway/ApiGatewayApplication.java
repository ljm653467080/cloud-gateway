package cn.com.imaginary.ms.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	/**
	 * IP 限流
	 *
	 * @return
	 */
//	@Bean(name = "ipKeyResolver")
//	public KeyResolver keyResolver() {
//		return new KeyResolver() {
//			@Override
//			public Mono<String> resolve(ServerWebExchange exchange) {
//				// 获取客户端 IP
//				String ip = Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getHostString();
//				System.out.println("用户请求的 IP：" + ip);
//				return Mono.just(ip);
//			}
//		};
//	}
}
