package jp.co.rororo.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AuthorityType {
	
	ROOT,
	ADMIN,
	USER,
	GUEST;
	
	private static final String ROLE_PREFIX = "ROLE_";
	
	public String toStringWithPrefix() {
		return ROLE_PREFIX + this.toString();
	}
}
