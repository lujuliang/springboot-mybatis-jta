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
    
    private boolean entire = true;
    private int maxage = 1000;
    private int smaxage = 1000;
    //default value:private
    private String policy = "private";
    
    private List< String> cacheURL = new ArrayList<String>();
    
    public boolean isEntire() {
        return entire;
    }

    public void setEntire(boolean entire) {
        this.entire = entire;
    }

    public int getMaxage() {
        return maxage;
    }

    public void setMaxage(int maxage) {
        this.maxage = maxage;
    }

    public int getSmaxage() {
        return smaxage;
    }

    public void setSmaxage(int smaxage) {
        this.smaxage = smaxage;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public List< String> getCacheURL() {
        return cacheURL;
    }

    public void setCacheURL(List< String> cacheURL) {
        this.cacheURL = cacheURL;
    }

}
