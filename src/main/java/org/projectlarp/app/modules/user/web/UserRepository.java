package org.projectlarp.app.modules.user.web;

import java.util.Optional;

import org.projectlarp.app.modules.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "users", collectionResourceRel = "items")
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	@RestResource(path = "filter", rel = "filters")
	Page<User> findAllByUsernameLikeIgnoreCase(@Param("username") String username, Pageable pageable);

	@EntityGraph(attributePaths = "authorities")
	Optional<User> findOneWithAuthoritiesById(Long id);

	@EntityGraph(attributePaths = "authorities")
	Optional<User> findOneWithAuthoritiesByUsername(String username);

	/* CUSTOM ********************* */

	User findByIdentityToken(String token);

	
}
