package springboot.mybatis.jta.mapper.database2;

import org.apache.ibatis.annotations.Insert;

import springboot.mybatis.jta.bean.database2.Income;

public interface IncomeMapper {
	
	    public void insert(Income income);

}
