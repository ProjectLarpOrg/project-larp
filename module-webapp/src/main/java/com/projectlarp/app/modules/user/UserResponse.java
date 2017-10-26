package com.projectlarp.app.modules.user;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Table(name = "USER")
@Data
public class UserResponse implements Serializable {

	@Transient
	private static final long serialVersionUID = -1920106832600156493L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	String username;
	String firstName;
	String lastName;
	String mail;
	String iso639Language;
}
