package com.projectlarp.app.common.csv;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Preconditions;

public class CSVHttp {

	public String nameWithDate(String namePrefix) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String fileName = namePrefix + "-" + sdf.format(new Date()) + ".csv";
		return fileName;
	}

	public PrintWriter prepareCSVHttpHeader(HttpServletResponse response,
			String fileName) throws IOException {
		response.setContentType("text/csv");
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				fileName);
		response.setContentType("text/csv");
		response.setHeader(headerKey, headerValue);
		PrintWriter writer = response.getWriter();
		return writer;
	}

	public void checkLimit(List<?> items, int limit) {
		Preconditions.checkState(items.size() < limit,
				String.format("TOO_MUCH_DATAS (%d)", items.size()));
	}

	@Deprecated
	public void checkNotEmpty(String... args) {
		boolean notEmpty = false;
		for (String arg : args) {
			if (null != arg && !arg.isEmpty() && !"%%".equals(arg))
				notEmpty = true;
		}
		Preconditions.checkState(notEmpty, "NO_ARGS");
	}
}
