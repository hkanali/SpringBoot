package jp.co.rororo.entity;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseEntity {
	
	private Date createAt;
	
	private Date updateAt;
	
	private boolean deleted;
	
	public void setSystemColums() {
		Date now = new Date();
		createAt = now;
		updateAt = now;
		deleted = false;
	}
	
}
