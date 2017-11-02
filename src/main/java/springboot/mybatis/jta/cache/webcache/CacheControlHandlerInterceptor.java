package springboot.mybatis.jta.cache.webcache;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import springboot.mybatis.jta.config.CacheControlConfig;


/*****
 * 
 * @Date 2017-09-26
 * See  https://github.com/foo4u/spring-mvc-cache-control/
 *
 */
@Component(value = "handlerInterceptor")
public class CacheControlHandlerInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor {

  
}