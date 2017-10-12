# springboot-mybatis-jta 
include:
1）mybatis
2）jta ： Atomkos
3） postgresql 
4)logback
5)ecache and webcache
6)

## Dependencies
1) update postgresql's install path /data/postgresql.conf ,set   max_prepared_transactions = 100
## Installing springboot-mybatis-jta
Run 'maven install', run in spring boot

Access  http://localhost:9090/test/income/addincome/1?name=test ，get message 'insert success'
Access http://localhost:9090/income/addincome/2?name=test， failed data not insert to two database

# refer http://www.hifreud.com/2017/07/12/spring-boot-23-jta-handle-distribute-transaction/

#Ecache
 1) add cache: 
    type: ehcache
    ehcache: 
      config: classpath:ehcache.xml  to application.yml
 2) create ehcache.xml add '<cache .../>' to this file.
 3) LookupServiceImpl.java , value = name（in  ehcache.xml）
 
#Webcache
 1)java\springboot\mybatis\jta\cache\webcache 
 2)java\springboot\mybatis\jta\config\WebMvcCacheConfig.java
 3)application.yml -> WebCache 
  