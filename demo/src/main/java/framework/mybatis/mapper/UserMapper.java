package framework.mybatis.mapper;

import framework.mybatis.modal.User;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User>{

	public User selectByName(String userName);
}
