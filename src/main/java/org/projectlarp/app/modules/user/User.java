package org.projectlarp.app.modules.user;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "USERS")
public class User implements UserDetails {

	private static final long serialVersionUID = -5162717307312605290L;

	public static final int PASSWORD_MIN_LENGTH = 8;
	public static final int PASSWORD_MAX_LENGTH = 100;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/* UserDetails properties ******************************** */

	@Column(nullable = false, unique = true)
	private String username;

	@Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
	private String password;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
	@BatchSize(size = 20)
	private Collection<Authority> authorities = new ArrayList<>();
	
	private boolean enabled = true;
	@Transient
	private boolean accountNonExpired = true;
	@Transient
	private boolean credentialsNonExpired = true;
	@Transient
	private boolean accountNonLocked = true;

	@Email
	@Size(min = 5, max = 100)
	private String email;

	@Size(min = 2, max = 6)
	private String langKey;

	@Size(min = 2, max = 6)
	private String themeKey;

	@OneToOne
	@JoinColumn(name = "id", referencedColumnName = "user_id")
	private Identity identity;
	@OneToOne
	@JoinColumn(name = "id", referencedColumnName = "user_id")
	private Profile profile;

	private String activationKey;
	private String resetKey;
	private Instant resetDate;

	/* Accessors *********************************************** */

	public void setPassword(String password) {
		if (isNotEncoded(password))
			password = passwordEncoder.encode(password);
		this.password = password;
	}

	private boolean isNotEncoded(String password) {
		return org.apache.commons.lang3.StringUtils.isNotBlank(password) && !password.startsWith("$2a$10$");
	}

	@Transient
	static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	/* Generated Accessors ************************************* */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean getEnabled() {
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

	public String getLangKey() {
		return langKey;
	}

	public void setLangKey(String langKey) {
		this.langKey = langKey;
	}

	public String getThemeKey() {
		return themeKey;
	}

	public void setThemeKey(String themeKey) {
		this.themeKey = themeKey;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	public String getResetKey() {
		return resetKey;
	}

	public void setResetKey(String resetKey) {
		this.resetKey = resetKey;
	}

	public Instant getResetDate() {
		return resetDate;
	}

	public void setResetDate(Instant resetDate) {
		this.resetDate = resetDate;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public User() {
		// Empty constructor needed for Jackson.
	}

	public User(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.enabled = user.getEnabled();
		this.langKey = user.getLangKey();
		this.authorities = user.getAuthorities().stream().map(i -> new Authority(i.getAuthority()))
				.collect(Collectors.toSet());
		this.authorities = user.authorities;
	}
}
