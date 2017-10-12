package springboot.mybatis.jta.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springboot.mybatis.jta.cache.webcache.CacheControlHandlerInterceptor;
/*****
 * 
 * @Date 2017-09-26
 * See  https://github.com/foo4u/spring-mvc-cache-control/
 *
 */

@Configuration
@EnableSpringConfigured
public class WebMvcCacheConfig extends WebMvcConfigurerAdapter implements WebMvcConfigurer
{
    @Autowired
    private CacheControlHandlerInterceptor handlerInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        if (handlerInterceptor == null){
            handlerInterceptor = new CacheControlHandlerInterceptor();
            handlerInterceptor.setCacheControlConfig(new CacheControlConfig());
        }
        registry.addInterceptor(handlerInterceptor);
    }
}