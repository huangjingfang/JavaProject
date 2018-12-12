package jlan.lab.Api.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

@Validated
public class User {
	@NotNull
	@Pattern(regexp="[a-z]{6}",message="长度或者字符不符合规范")
	private String name;
	@NotNull
	@Pattern(regexp="[*] {6}",message="长度不符合规范")
	private String password;
	
	@Pattern(regexp="[*]{0-16}")
	private String notes;
	
	
	public static void main(String[] args) {
		User user = new User();
//		Assert.notNull(null, "not null");
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<User>> set = validator.validate(user, User.class);
		for(ConstraintViolation<User> validation:set) {
			System.out.println(validation.toString());
		}
		test(user);
		
	}
	public static void test(@Valid User user) {
		
	}
}
