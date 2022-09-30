//package cn.com.imaginary.ms.apigateway.config;
//
//import cn.com.imaginary.ms.apigateway.core.filter.LogInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.annotation.Resource;
//
//@Configuration
//public class InterceptorConfig implements WebMvcConfigurer {
//
//    @Resource
//    private LogInterceptor logInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(logInterceptor).addPathPatterns("/**");
//    }
//}
