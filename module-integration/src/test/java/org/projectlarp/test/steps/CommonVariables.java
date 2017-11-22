package org.projectlarp.test.steps;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CommonVariables {

	// INIT / DESTROY
	static {
		// TODO clear vall vars
	}

	// COMMON TEST VARS (used for communication between steps methods)

	public static Date startDate;

	public static void newStartDate() {
		startDate = new Date(System.currentTimeMillis() - 1000);
	}
}
