package com.projectlarp.app.modules.profile;

import lombok.*;
import javax.persistence.*;
import com.projectlarp.app.common.domain.*;

@Entity
@Table(name = "PROFILE")
@Data
@EqualsAndHashCode(callSuper=false)
public class Profile extends AbstractDomainClass {
	
	String firstName;
	String lastName;
	String iso639Language;
}
