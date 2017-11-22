package org.projectlarp.test.util;

import java.util.Properties;

public class SSHCommandFactoryLog extends SSHCommandFactory {

	public SSHCommandFactoryLog(Properties props) {
		super( //
				props, //
				props.getProperty("logs.path"));
	}
}
