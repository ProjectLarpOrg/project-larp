package com.projectlarp.app.modules.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @see origin
 *      http://www.baeldung.com/spring-security-authentication-with-a-database
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
