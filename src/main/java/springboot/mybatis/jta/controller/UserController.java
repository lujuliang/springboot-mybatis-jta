package springboot.mybatis.jta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.mybatis.jta.bean.database1.User;
import springboot.mybatis.jta.service.UserServiceImpl;

/**

 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/all")
    public List<User> all() {
        return userService.getAllUsers();
    }

   
}