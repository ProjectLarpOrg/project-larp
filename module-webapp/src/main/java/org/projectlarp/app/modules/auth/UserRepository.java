package org.projectlarp.app.modules.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @see origin
 *      http://www.baeldung.com/spring-security-authentication-with-a-database
 */
@RepositoryRestResource(path = "users", collectionResourceRel = "items")
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
	@RestResource(path = "filter", rel = "filters")
	Page<User> findAllByUsernameLikeIgnoreCase( //
			@Param("username") String username, //
			Pageable pageable);
}
