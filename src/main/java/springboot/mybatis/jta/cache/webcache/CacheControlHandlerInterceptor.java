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

    private static final String HEADER_EXPIRES = "Expires";
    private static final String HEADER_CACHE_CONTROL = "Cache-Control";
    private static final String REG_PATH_REPLACE =  "\\{.*?\\}";

    private boolean useExpiresHeader = true;
    
    @Autowired
    private CacheControlConfig cacheControlConfig;
    
    
    /**
     * Creates a new cache control handler interceptor.
     */
    public CacheControlHandlerInterceptor() {
        super();
    }
    
    /**
     * Assigns a <code>CacheControl</code> header to the given <code>response</code>.
     * 
     * @param request the <code>HttpServletRequest</code>
     * @param response the <code>HttpServletResponse</code>
     * @param handler the handler for the given <code>request</code>
     */
    
    protected boolean match(String[] urlStrArray, String[] entireUrlArray){
        if (urlStrArray == null || entireUrlArray == null || (urlStrArray.length != entireUrlArray.length)){
            return false;
        }

        Pattern p = Pattern.compile(REG_PATH_REPLACE);
        for (int i = 0; i < entireUrlArray.length; i++){
            String t1 = urlStrArray[i];
            String t2 = entireUrlArray[i];
            if (p.matcher(t1).find()){
                continue;
            }
            if (t1 == null || !t1.equals(t2)){
                return false;
            }  
        }
        return true;
    }
    protected final void assignCacheControlHeader(
            final HttpServletRequest request,
            final HttpServletResponse response, 
            final Object handler) {
    	
        List<String> urlList = this.cacheControlConfig.getCacheURL();
        if (urlList == null || urlList.isEmpty()) {
            return;
        }
        String url, entireURL;
        url = request.getServletPath();
        String queryStr = request.getQueryString();
        if (queryStr != null && !"".equals(queryStr)) {
            if ("/".equals(url.substring((url.length() - 1)))) {
                entireURL = url.substring(0, (url.length() - 1)) + "?" + queryStr;
            } else {
                entireURL = url + "?" + queryStr;
            }
        } else {
            entireURL = url;
        }
        //cache url
        processURL(response, urlList, url, entireURL);
        
        //method annotation
        final CacheControl cacheControl = this.getCacheControl(request, response, handler);
        final String cacheControlHeader = this.createCacheControlHeader(cacheControl);
        
        if (cacheControlHeader != null){
            response.setHeader(HEADER_CACHE_CONTROL, cacheControlHeader);
            if (useExpiresHeader){
                response.setDateHeader(HEADER_EXPIRES, createExpiresHeader(cacheControl));
            }
        }
    }
    private void processURL(final HttpServletResponse response, List<String> urlList, String url, String entireURL){
        for (String urlStr : urlList) {

            // procces @PathVariable
            Pattern p = Pattern.compile(REG_PATH_REPLACE);

            if (p.matcher(urlStr).find()) {
                String[] entireUrlArray = url.split("/");
                String[] urlStrArray = urlStr.split("/");
                if (cacheControlConfig.isEntire()) {
                    entireUrlArray = entireURL.split("/");
                }
                if (match(urlStrArray, entireUrlArray)) {
                    setCacheHeader(response);
                    break;
                }
            } else {
                if (cacheControlConfig.isEntire() && urlStr.equals(entireURL)) {// contain cache entire url
                    setCacheHeader(response);
                    break;
                } else if (urlStr.equals(url)) {
                    setCacheHeader(response);
                    break;
                }
            }

        }
    }
    protected void setCacheHeader(final HttpServletResponse response){
        final String cacheControlHeader = this.createCacheControlHeader(cacheControlConfig.getMaxage(),
                cacheControlConfig.getSmaxage(), this.cacheControlConfig.getPolicy());
        response.setHeader(HEADER_CACHE_CONTROL, cacheControlHeader);
        if (useExpiresHeader){
            response.setDateHeader(HEADER_EXPIRES, createExpiresHeader(cacheControlConfig.getMaxage()));
        }
        
    }
    /**
     * Returns cache control header value from the given {@link CacheControl}
     * annotation.
     * 
     * @param cacheControl the <code>CacheControl</code> annotation from which to
     * create the returned cache control header value
     * 
     * @return the cache control header value
     */
    protected final String createCacheControlHeader(final CacheControl cacheControl){

        final StringBuilder builder = new StringBuilder();

        if (cacheControl == null) {
            return null;
        }

        final CachePolicy[] policies = cacheControl.policy();

        if (cacheControl.maxAge() >= 0) {
            builder.append("max-age=").append(cacheControl.maxAge());
        }

        if (cacheControl.sharedMaxAge() >= 0) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            builder.append("s-maxage=").append(cacheControl.sharedMaxAge());
        }

        if (policies != null) {
            for (final CachePolicy policy : policies) {
                if (builder.length() > 0) {
                    builder.append(", ");
                }
                builder.append(policy.policy());
            }
        }

        return (builder.length() > 0 ? builder.toString() : null);
    }
    
    protected final String createCacheControlHeader(final int maxage, final int smaxage, String policy){

        final StringBuilder builder = new StringBuilder();

        if (maxage >= 0) {
            builder.append("max-age=").append(maxage);
        }

        if (smaxage >= 0) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            builder.append("s-maxage=").append(smaxage);
        }
        if (policy != null) {

                if (builder.length() > 0) {
                    builder.append(", ");
                }
                builder.append(policy);

        }

        return (builder.length() > 0 ? builder.toString() : null);
    }
    /**
     * Returns an expires header value generated from the given 
     * {@link CacheControl} annotation.
     * 
     * @param cacheControl the <code>CacheControl</code> annotation from which to
     * create the returned expires header value
     * 
     * @return the expires header value
     */
    protected final long createExpiresHeader(final CacheControl cacheControl){
        
        final Calendar expires = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        
        if (cacheControl.maxAge() >= 0) {
            expires.add(Calendar.SECOND, cacheControl.maxAge());
        }
        
        return expires.getTime().getTime();
    }
    
    protected final long createExpiresHeader(final int maxage){
        
        final Calendar expires = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        
        if (maxage >= 0) {
            expires.add(Calendar.SECOND, maxage);
        }
        
        return expires.getTime().getTime();
    }
    
    /**
     * Returns the {@link CacheControl} annotation specified for the
     * given request, response and handler.
     * 
     * @param request the current <code>HttpServletRequest</code>
     * @param response the current <code>HttpServletResponse</code>
     * @param handler the current request handler
     * 
     * @return the <code>CacheControl</code> annotation specified by
     * the given <code>handler</code> if present; <code>null</code> otherwise
     */
    protected final CacheControl getCacheControl(
            final HttpServletRequest request,
            final HttpServletResponse response, 
            final Object handler) {
        
        if (handler == null || !(handler instanceof HandlerMethod)) {
            return null;
        }
        
        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        CacheControl cacheControl = handlerMethod.getMethodAnnotation(CacheControl.class);
        
        if (cacheControl == null) {
            return handlerMethod.getBeanType().getAnnotation(CacheControl.class);
        }
        
        return cacheControl;
    }
    
    @Override
    public final boolean preHandle(
            final HttpServletRequest request,
            final HttpServletResponse response, 
            final Object handler) throws Exception {
        
        this.assignCacheControlHeader(request, response, handler);
        
        return super.preHandle(request, response, handler);
    }

    /**
     * True to set an expires header when a {@link CacheControl} annotation is present
     * on a handler; false otherwise.   Defaults to true.
     * 
     * @param useExpiresHeader <code>true</code> to set an expires header when a 
     * <code>CacheControl</code> annotation is present on a handler; <code>false</code> otherwise
     */
    public final void setUseExpiresHeader(final boolean useExpiresHeader) {
        this.useExpiresHeader = useExpiresHeader;
    }

    public CacheControlConfig getCacheControlConfig() {
        return cacheControlConfig;
    }

    public void setCacheControlConfig(CacheControlConfig cacheControlConfig) {
        this.cacheControlConfig = cacheControlConfig;
    }
    
}