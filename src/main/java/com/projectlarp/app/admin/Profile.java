package com.projectlarp.app.admin;

import com.projectlarp.app.security.domain.model.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "SEC_USERS")
public class Profile {
    @Id
    public String username;
    public boolean enabled = true;
}
