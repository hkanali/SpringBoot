package jp.co.rororo.entity;

import java.io.Serializable;

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
public class RememberMeToken extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String series;
	
	private String token;
	
	private Long userId;
}
