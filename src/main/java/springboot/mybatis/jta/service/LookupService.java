package springboot.mybatis.jta.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import springboot.mybatis.jta.bean.database1.User;
import springboot.mybatis.jta.cache.CacheNames;

public interface LookupService {

	List<User> getAllUsers();
	
}
