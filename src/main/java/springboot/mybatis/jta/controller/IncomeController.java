package springboot.mybatis.jta.controller;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springboot.mybatis.jta.bean.database1.User;
import springboot.mybatis.jta.bean.database2.Income;
import springboot.mybatis.jta.mapper.database1.UserMapper;
import springboot.mybatis.jta.mapper.database2.IncomeMapper;



/**
 */
@RestController
@RequestMapping("/income")
public class IncomeController {

    public static final String RESULT_SUCCESS = "success";
    public static final String RESULT_FAILED = "failed";

    @Autowired
    private IncomeMapper incomeMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/addincome/1")
    @Transactional //   move @Transactional to service layer
    public String addIncome1(@RequestParam("name") String name) {

            User user = new User();
            user.setName(name);
            userMapper.insert(user);

            Income income = new Income();
            income.setUserId(user.getId());
            income.setOperateDate(new Date());
            incomeMapper.insert(income);

            return RESULT_SUCCESS;

    }

    @GetMapping("/addincome/2")
    @Transactional
    public String addIncome2(@RequestParam("name") String name) {
            User user = new User();
            user.setName(name);
            userMapper.insert(user);

            this.throwRuntimeException();

            Income income = new Income();
            income.setUserId(user.getId());
            income.setOperateDate(new Date());
            incomeMapper.insert(income);

            return RESULT_SUCCESS;

    }

    public void throwRuntimeException() {
        throw new RuntimeException("User defined exceptions");
    }
}
