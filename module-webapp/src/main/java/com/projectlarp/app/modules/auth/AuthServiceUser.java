package com.projectlarp.app.modules.auth;

import java.util.List;
import lombok.Data;

@Data
public class AuthServiceUser {

	String login;
	String iso639Language;

	String firstName;
	String lastName;
	String mail;

	List<String> roles;
}
