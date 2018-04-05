package com.krw.spring.constants;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoles implements GrantedAuthority {

	END_USER, SERVICE_PROVIDER, ADMIN;

	@Override
	public String getAuthority() {
		return this.name();
	}

}
