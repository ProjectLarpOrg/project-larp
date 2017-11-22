package org.projectlarp.app.modules.auth;

public enum Role {
	ADMIN, USER;

	public String authority() {
		return "ROLE_" + this.name();
	}
}