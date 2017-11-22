package org.projectlarp.app.modules.auth;

import java.util.*;
import javax.persistence.*;
import com.projectlarp.app.common.domain.*;
import org.projectlarp.app.common.domain.AbstractDomainClass;

@Entity
@Table(name = "USER")
public class User extends AbstractDomainClass {

	@Column(nullable = false, unique = true)
	public String username;
	public String password;

	public boolean enabled = true;
	public boolean accountNonExpired = true;
	public boolean credentialsNonExpired = true;
	public boolean accountNonLocked = true;

    @OneToMany
    @JoinColumn(name="APP_USER_ID", referencedColumnName="ID")
    public List<UserRole> roles = new ArrayList<>();;
}
