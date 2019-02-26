package framework.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import framework.mybatis.modal.User;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User>{

	public User selectByName(String userName);
	
	@Select("select * from userinfo")
	public List<User> selectAll();
}
