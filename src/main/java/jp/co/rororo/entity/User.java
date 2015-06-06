package jp.co.rororo.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	private String email;
	
	private String bio;
	
	private Date createAt;
	
	private Date updateAt;
	
	private boolean deleted;
	
}
