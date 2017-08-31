package springboot.mybatis.jta.mapper.database1;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import springboot.mybatis.jta.bean.database1.User;

public interface UserMapper {
	   @Select("SELECT * FROM public.\"user\"")
	    public List<User> getAll();

	    public User getById(@Param("id") int id);

	    public void insert(User user);

	    public void delete(@Param("id") int id);

}
