package org.projectlarp.app.modules.admin;

import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Embeddable
public class Identity {

//	private String id_token;
	private String token;
//	private String aud;
//	private String sub;
	
	@Basic
	@Temporal(TemporalType.DATE)
	private Calendar tokenExpirationDate;
	@Transient
	private final static long EXPIRE_IN = 3600;

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