package org.projectlarp.app.modules.user;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class Profile {

	@Size(max = 50)
	private String firstName;
	@Size(max = 50)
	private String lastName;
	private String birthDate;

	@Size(max = 100)
	private String name;
	@Size(max = 1000)
	private String bio;
	@Size(max = 256)
	private String imageUrl;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
}
