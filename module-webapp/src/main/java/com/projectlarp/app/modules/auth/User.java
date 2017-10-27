package com.projectlarp.app.modules.auth;

import lombok.*;
import java.util.*;
import javax.persistence.*;
import com.projectlarp.app.common.domain.*;

@Entity
@Table(name = "USER")
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends AbstractDomainClass {

	@Column(nullable = false, unique = true)
	String username;
	String password;

	boolean enabled = true;
	boolean accountNonExpired = true;
	boolean credentialsNonExpired = true;
	boolean accountNonLocked = true;

    @OneToMany
    @JoinColumn(name="APP_USER_ID", referencedColumnName="ID")
    private List<UserRole> roles = new ArrayList<>();;
}
