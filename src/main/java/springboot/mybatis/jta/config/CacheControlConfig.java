package springboot.mybatis.jta.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
/*****
 * 
 * @Date 2017-09-26
 * See  https://github.com/foo4u/spring-mvc-cache-control/
 *
 */

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "WebCache")
public class CacheControlConfig {
    
   

}
