package jp.co.rororo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class RememberMeToken extends BaseEntity {
	
	@Id
	private String series;
	
	private String token;
	
	private Long userId;
}
