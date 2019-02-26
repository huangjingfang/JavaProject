package framework.mybatis.modal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name="userinfo")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int ID;
	
	@Column(name="sex")
	private String sex;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="UserName")
	private String userName;
	
}
