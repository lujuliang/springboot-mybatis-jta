package springboot.mybatis.jta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.mybatis.jta.bean.database1.User;
import springboot.mybatis.jta.mapper.database1.UserMapper;

@Service
public class UserServiceImpl {

	@Autowired
    private UserMapper userMapper;
	
	public List<User> getAllUsers(){
		return userMapper.getAll();
	}
	
	public void deleteById(int id){
		userMapper.delete(id);
	}
	
	
}
