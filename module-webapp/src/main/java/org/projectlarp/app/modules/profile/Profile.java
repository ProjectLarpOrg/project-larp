package org.projectlarp.app.modules.profile;

import javax.persistence.*;
import org.projectlarp.app.common.domain.*;

@Entity
@Table(name = "PROFILE")
public class Profile extends AbstractDomainClass {
	
	public String name;
	public String bio;
	
	public String iso639Language;
}
