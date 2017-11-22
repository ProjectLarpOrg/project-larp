package org.projectlarp.app.modules.profile;

import javax.persistence.*;
import com.projectlarp.app.common.domain.*;
import org.projectlarp.app.common.domain.AbstractDomainClass;

@Entity
@Table(name = "PROFILE")
public class Profile extends AbstractDomainClass {
	
	public String firstName;
	public String lastName;
	public String iso639Language;
}
