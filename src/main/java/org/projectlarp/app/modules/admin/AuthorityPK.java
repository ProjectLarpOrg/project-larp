package org.projectlarp.app.modules.admin;

import java.io.Serializable;

public class AuthorityPK implements Serializable {
	private static final long serialVersionUID = 1322120000551624359L;

	public String username;
	public String authority;

	public AuthorityPK() { }

	public AuthorityPK(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }
}