//package cn.com.imaginary.ms.apigateway.core.filter;
//
//import cn.com.imaginary.ms.apigateway.common.constants.StringConsts;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.MDC;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.UUID;
//
///**
// * 后续如果有RPC远程调用，可以再新增一个FeignInterceptor。多个微服务之间调用，打印的tranceId应该是一致的
// * MDC 对整个线程是全局的
// */
//@Component
//public class LogInterceptor extends HandlerInterceptorAdapter {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String traceId = request.getHeader(StringConsts.TRACE_ID);
//        if (StringUtils.isEmpty(traceId)) {
//            MDC.put(StringConsts.TRACE_ID, UUID.randomUUID().toString());
//        } else {
//            MDC.put(StringConsts.TRACE_ID, traceId);
//        }
//
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        //防止内存泄露
//        MDC.remove(StringConsts.TRACE_ID);
//    }
//
//}
