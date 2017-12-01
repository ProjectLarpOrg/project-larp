package org.projectlarp.app.modules.admin;

import java.util.*;
import javax.persistence.*;

import org.projectlarp.app.common.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "USERS")
public class User implements UserDetails {

	private static final long serialVersionUID = -5162717307312605290L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;

	/* UserDetails properties ******************************** */

	@Column(nullable = false, unique = true)
	private String username;
	private String password;
	private boolean enabled = true;
	@Transient
	private boolean accountNonExpired = true;
	@Transient
	private boolean credentialsNonExpired = true;
	@Transient
	private boolean accountNonLocked = true;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
	public Collection<Authority> authorities = new ArrayList<>();

	/* Custom properties **************************************** */

	private String email;

	private String token;
	@Basic
	@Temporal(TemporalType.DATE)
	private Calendar tokenExpirationDate;

	/* Accessors *********************************************** */

	public void setToken(String token) {
		this.token = token;
		Calendar now = Calendar.getInstance();
		this.tokenExpirationDate = now;
	}

	public boolean isTokenValid() {
		Calendar now = Calendar.getInstance();
		return token != null && tokenExpirationDate.before(now);
	}

	public void setPassword(String password) {
		if (isNotEncoded(password))
			password = passwordEncoder.encode(password);
		this.password = password;
	}

	private boolean isNotEncoded(String password) {
		return StringUtils.isNotBlank(password) && !password.startsWith("$2a$10$");
	}

	@Transient
	static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	/* Generated Accessors ************************************* */

	public String getToken() {
		return token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
