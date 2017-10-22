package com.projectlarp.app.modules.auth;

import lombok.extern.log4j.Log4j;

@Log4j
public class AuthStats {

	public void log(String statType, String... datas) {
		String message = "";
		for (String data : datas) {
			message += data + ";";
		}
		log.info(statType + ";" + message);
	}
}
