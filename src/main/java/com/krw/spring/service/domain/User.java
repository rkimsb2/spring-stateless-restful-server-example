package com.krw.spring.service.domain;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.krw.spring.constants.UserRoles;

public class User implements UserDetails, CredentialsContainer {
	
	public static class View {
		public interface EndUser{}
		public interface EndUserMe extends EndUser {}
		public interface ServiceProvider extends EndUser {}
		public interface Admin extends ServiceProvider {}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 378229664586952104L;

	@JsonView(User.View.Admin.class)
	private int userSeq;
	private String password;
	
	@JsonView(User.View.EndUser.class)
	private String username;
	@JsonView(User.View.Admin.class)
	private UserRoles authority = UserRoles.END_USER;
	@JsonView(User.View.Admin.class)
	private boolean accountNonExpired;
	@JsonView(User.View.Admin.class)
	private boolean accountNonLocked;
	@JsonView(User.View.Admin.class)
	private boolean credentialsNonExpired;
	@JsonView(User.View.Admin.class)
	private boolean enabled;

	@JsonView({ User.View.Admin.class, User.View.ServiceProvider.class, User.View.EndUserMe.class })
	private UUID uuid; // only for verifier. Do NOT use for processing CUD.
	@JsonView({ User.View.ServiceProvider.class, User.View.EndUserMe.class })
	private String email;
	@JsonView({ User.View.ServiceProvider.class, User.View.EndUserMe.class })
	private String nickname;
	@JsonView({ User.View.Admin.class, User.View.EndUserMe.class })
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
	private Date created;

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	@Override
	public Collection<UserRoles> getAuthorities() {
		return Arrays.asList(authority);
	}

	public UserRoles getAuthority() {
		return authority;
	}

	public void setAuthority(UserRoles authority) {
		if (authority != null) {
			this.authority = authority;
		}
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	// naive UUID setter
	public void setUuid(String uuidStr) throws IllegalArgumentException {
		setUuid(UUID.fromString(uuidStr));
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public void eraseCredentials() {
		this.password = null;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
