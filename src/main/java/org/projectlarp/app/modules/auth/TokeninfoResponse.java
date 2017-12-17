package org.projectlarp.app.modules.auth;

public class TokeninfoResponse {

	public String sub;
	public String iss;
	public String aud;
	public Long auth_time;
	public String acr;
	public Long exp;

	public TokeninfoResponse(String aud, Long exp) {
		super();
		this.iss = "www.project-larp.org";
		this.aud = aud;
		this.acr = "project-larp.json";
		this.exp = exp;
	}

}
