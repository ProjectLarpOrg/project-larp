package org.projectlarp.app.modules.profile;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

import org.projectlarp.app.modules.auth.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/api/users/{userId}/profil")
public class ProfilController {

	@Autowired
	ProfilRepository profileRepository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Profil> get( //
			@PathVariable Long userId) {
		Profil entity = this.profileRepository //
				.findByUserId(userId);
		HttpStatus status = (entity == null) ? BAD_REQUEST : OK;
		return new ResponseEntity<Profil>(entity, status);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Profil> post( //
			@PathVariable Long userId, //
			@RequestBody Profil request) {
		Profil entity = this.profileRepository //
				.findByUserId(userId);
		if (entity == null) {
			// TODO
		} else {
			entity.language = request.language;
			entity.theme = request.theme;
		}
		this.profileRepository //
				.save(entity);
		return new ResponseEntity<Profil>(entity, OK);
	}
}
