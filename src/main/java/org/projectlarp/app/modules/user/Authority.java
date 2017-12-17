package org.projectlarp.app.modules.user;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.projectlarp.app.config.Constants;
import org.projectlarp.app.modules.user.Authority;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name =  "AUTHORITIES")
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = -111819464029246248L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@NotNull
	@Size(max = 50)
	@Id
	@Column(length = 50)
	public String authority;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

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
	
}