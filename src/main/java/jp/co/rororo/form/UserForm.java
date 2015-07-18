package jp.co.rororo.form;

import javax.validation.constraints.Size;

import lombok.Data;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class UserForm {
	
	@NotEmpty
	@Size(max = 10)
	private String name;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	@Size(max = 100)
	private String bio;
}
