package org.projectlarp.app.modules.game;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.projectlarp.app.common.domain.AuditInformation;

@Entity
@Table(name = "GAMES")
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Embedded
	private AuditInformation auditInformation;
}
