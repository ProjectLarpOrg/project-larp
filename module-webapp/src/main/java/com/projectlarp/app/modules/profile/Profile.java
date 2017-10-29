package com.projectlarp.app.modules.profile;

import javax.persistence.*;
import com.projectlarp.app.common.domain.*;

@Entity
@Table(name = "PROFILE")
public class Profile extends AbstractDomainClass {
	
	public String firstName;
	public String lastName;
	public String iso639Language;
}
