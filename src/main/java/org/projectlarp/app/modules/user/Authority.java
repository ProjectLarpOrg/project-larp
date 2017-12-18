package org.projectlarp.app.modules.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "AUTHORITIES")
public class Authority implements GrantedAuthority {
	
	private static final long serialVersionUID = -111819464029246248L;

	@NotNull
	@Size(max = 50)
	@Id
	@Column(length = 50)
	private String authority;
	
	/* ACCESSORS *************************** */

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Authority authority = (Authority) o;

		return !(authority != null ? !authority.equals(authority.authority) : authority.authority != null);
	}

	@Override
	public int hashCode() {
		return authority != null ? authority.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Authority{" + "authority='" + authority + '\'' + "}";
	}

	public Authority() {
	}

	public Authority(String authority) {
		this.authority = authority;
	}
	
}