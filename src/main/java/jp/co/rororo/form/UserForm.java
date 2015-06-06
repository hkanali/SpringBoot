package jp.co.rororo.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import lombok.Data;

@Data
public class UserForm {
	
	@NotNull
	@Size(max = 10)
	private String name;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	@Size(max = 100)
	private String bio;
}
