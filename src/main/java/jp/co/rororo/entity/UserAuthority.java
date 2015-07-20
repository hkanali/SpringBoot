package jp.co.rororo.entity;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import jp.co.rororo.type.AuthorityType;
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
public class UserAuthority extends BaseEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Long userId;
	
	@Enumerated
	private AuthorityType authorityType;
}
