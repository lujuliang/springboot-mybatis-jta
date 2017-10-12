package springboot.mybatis.jta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import springboot.mybatis.jta.bean.database1.User;
import springboot.mybatis.jta.cache.CacheNames;
import springboot.mybatis.jta.mapper.database1.UserMapper;
import springboot.mybatis.jta.service.LookupService;
@Service
@EnableCaching
public class LookupServiceImpl implements LookupService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	@Cacheable(value = CacheNames.LOOKUP_CACHE, key = "'getAllUsers'")
	public List<User> getAllUsers() {
		return userMapper.getAll();
	}

}
