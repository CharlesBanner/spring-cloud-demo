package zuul.filter;

import com.bbd.commons.lang.rt.EnumReturnValue;
import com.bbd.commons.lang.rt.JsonRet;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName SecurityWebFilter
 * @Description TODO
 * @Author suyin
 * @Date 2019/5/6
 * @Version V1.0
 **/
@Configuration
public class SecurityWebFilter {

    @Bean
    public TokenFilter tokenFilter(){
        return new TokenFilter();
    }
}


class TokenFilter implements GlobalFilter, Ordered {

    Logger logger= LoggerFactory.getLogger( TokenFilter.class );
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("sso_token");
        if (StringUtils.isBlank(token)){
            token = exchange.getRequest().getHeaders().getFirst("sso_token");
        }
        if (token == null || token.isEmpty()) {
            logger.info( "token is empty..." );
            ServerHttpResponse originalResponse = exchange.getResponse();
            originalResponse.setStatusCode(HttpStatus.OK);
            byte[] response = JsonRet.buildRet(EnumReturnValue.S_NOTAUTH).toString().getBytes(StandardCharsets.UTF_8);
            originalResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            DataBuffer buffer = originalResponse.bufferFactory().wrap(response);
            return originalResponse.writeWith(Flux.just(buffer));
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }
}

