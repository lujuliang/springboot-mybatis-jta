package springboot.mybatis.jta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.mybatis.jta.bean.database1.User;

import springboot.mybatis.jta.service.LookupService;

/**
 */
@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
	private LookupService looupService;
	

	@GetMapping("/lookup/users")
	public List<User> getAllCountries() {
		return looupService.getAllUsers();
	}

}
