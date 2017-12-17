package org.projectlarp.app.modules.scenario;

import javax.persistence.Embedded;
import org.projectlarp.app.common.domain.AuditInformation;

public class Scenario {

	@Embedded
	private AuditInformation auditInformation;

}
