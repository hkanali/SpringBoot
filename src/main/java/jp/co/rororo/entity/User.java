package jp.co.rororo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table
@Entity
public class User extends BaseEntity {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	private String email;
	
	private String bio;
	
}
