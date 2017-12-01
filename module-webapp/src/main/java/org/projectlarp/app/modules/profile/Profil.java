package org.projectlarp.app.modules.profile;

import javax.persistence.*;
import org.projectlarp.app.common.domain.*;

@Entity
@Table(name = "PROFILS")
public class Profil extends AbstractDomainClass {
	
	public String name;
	public String bio;
	
	public String language;
	public String theme;
	
	public Long userId;
}
