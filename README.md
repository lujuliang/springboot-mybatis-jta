# springboot-mybatis-jta
1）集成mybatis
2）jta ： Atomkos事务管理器
3）数据库 ： postgresql 
## Dependencies
1) 修改 postgresql 安装包下 /data/postgresql.conf 的 max_prepared_transactions 把注释去掉 改为 100
## Installing springboot-mybatis-jta
Run 'maven install', run in spring boot

访问 http://localhost:9090/test/income/addincome/1?name=test ，正常在两个数据库各插入一条数据。
访问http://localhost:9090/income/addincome/2?name=test，程序中会抛出一个运行时异常，事务失败，两个库都不会插入数据成功。


# 参考 http://www.hifreud.com/2017/07/12/spring-boot-23-jta-handle-distribute-transaction/
