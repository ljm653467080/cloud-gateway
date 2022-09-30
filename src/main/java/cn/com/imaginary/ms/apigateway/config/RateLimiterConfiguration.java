
package cn.com.imaginary.ms.apigateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2018/11/12 11:26
 * @description: IP限流配置
 */
@Configuration
public class RateLimiterConfiguration {

	@Bean(value = "remoteAddrKeyResolver")
	public KeyResolver remoteAddrKeyResolver() {
		return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
	}

}
