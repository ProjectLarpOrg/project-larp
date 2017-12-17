package org.projectlarp.app.modules.user;

import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Embeddable
public class Identity {

	public static final long EXPIRE_IN = 3600;
	
	private String token;
	
	@Basic
	@Temporal(TemporalType.DATE)
	private Calendar tokenExpirationDate;

	/* ACCESSORS *************************************** */

	public boolean isTokenValid() {
		Calendar now = Calendar.getInstance();
		return token != null && tokenExpirationDate.before(now);
	}

	public void setToken(String token) {
		this.token = token;
		Calendar now = Calendar.getInstance();
		this.tokenExpirationDate = now;
	}

	public String getToken() {
		return token;
	}
	
	public Calendar getTokenExpirationDate() {
		return tokenExpirationDate;
	}

	public void setTokenExpirationDate(Calendar tokenExpirationDate) {
		this.tokenExpirationDate = tokenExpirationDate;
	}

}