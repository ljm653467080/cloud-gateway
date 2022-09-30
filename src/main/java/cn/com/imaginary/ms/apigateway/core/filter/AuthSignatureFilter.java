package cn.com.imaginary.ms.apigateway.core.filter;

import cn.com.imaginary.ms.apigateway.common.constants.StringConsts;
import cn.com.imaginary.ms.apigateway.core.JwtHelper;
import cn.com.imaginary.ms.apigateway.core.whiteblacklist.ApiWhitelistResolver;
import cn.com.imaginary.ms.apigateway.model.ReturnData;
import cn.com.imaginary.ms.apigateway.util.ReturnDataUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2018/11/13 15:33
 */
@Slf4j
@Component
public class AuthSignatureFilter implements GlobalFilter, Ordered {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ApiWhitelistResolver apiWhitelistResolver;

    private static final String EXPIRE = "expire";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String uri = request.getURI().getPath();
        log.info("get request uri {}", uri);
        if (!apiWhitelistResolver.isWhitelistApi(uri)) {
            boolean hasToken = true; //true：令牌在头文件中;  false：令牌不在头文件中-》将令牌封装到头文件中，再传递给其他微服务
            //1.令牌在header中
            String token = exchange.getRequest().getHeaders().getFirst(StringConsts.X_AUTH_TOKEN);
            //2.令牌在参数中
            if (StringUtils.isBlank(token)) {
                token = request.getQueryParams().getFirst(StringConsts.X_AUTH_TOKEN);
                hasToken = false;
            }
            if (StringUtils.isBlank(token)) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                //响应空数据
                return response.setComplete();
            }
            if (!hasToken) {
                if (!token.startsWith("bearer ") && !token.startsWith("Bearer ")) {
                    token = "bearer " + token;
                }
                //将令牌封装到头文件中
                request.mutate().header(StringConsts.X_AUTH_TOKEN, token);
            }
            return chain.filter(exchange);
//            此处不使用JWT鉴权
//            ReturnData tokenData = JwtHelper.valid(token);
//            //校验token TODO
//            if (tokenData == null || !ReturnDataUtil.isSuccess(tokenData)) {
//                exchange.getResponse().setStatusCode(HttpStatus.OK);
//                exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
//                return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(getUnAuthString().getBytes())));
//            }
        }
        return chain.filter(exchange);
    }

    private String getUnAuthString() {
        return JSONObject.toJSONString(ReturnDataUtil.unAuth());
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
