package com.projectlarp.app.admin;

import com.projectlarp.app.security.domain.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.*;

@RepositoryRestResource(path = "profiles", collectionResourceRel = "profiles")
public interface ProfileRepository extends JpaRepository<Profile, String> {

}

