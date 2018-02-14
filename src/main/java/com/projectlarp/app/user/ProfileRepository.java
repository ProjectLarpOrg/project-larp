package com.projectlarp.app.user;

import com.projectlarp.app.security.domain.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.*;

@RepositoryRestResource(path = "users", collectionResourceRel = "users")
public interface ProfileRepository extends JpaRepository<Profile, Long> {

}

