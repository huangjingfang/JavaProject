package top.yansite.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.yansite.springboot.model.UserInfo;
import top.yansite.springboot.services.interfaces.IUserInfoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {
	@Autowired
	IUserInfoService service;
	
	@Test
	public void contextLoads() {
//		IUserInfoService service = (IUserInfoService) BeanUtils.getBean("UserInfoService");
		
		UserInfo info = service.getById(1);
		System.out.println(info.getUserName()+"\t"+info.getPassword());
	}

}

