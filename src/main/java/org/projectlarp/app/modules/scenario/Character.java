package org.projectlarp.app.modules.scenario;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.projectlarp.app.common.domain.AuditInformation;

@Entity
@Table(name = "CHARACTERS")
public class Character {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Embedded
	private AuditInformation auditInformation;
}
