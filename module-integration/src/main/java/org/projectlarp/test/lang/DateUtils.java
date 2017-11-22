package org.projectlarp.test.lang;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String newDate_HHmmssSSS() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss,SSS");
		Date date = new Date();
		return " at " + sdf.format(date);
	}

	public static String newDate_yyyyMMdd_HHmmss() {
		return new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	}
}
