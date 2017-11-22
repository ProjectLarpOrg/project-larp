package org.projectlarp.app.common.search;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;

import org.projectlarp.app.common.lang.StringUtils;

public class SearchHelper {

	public static final String ISODateTime = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"; // ISO8601DateFormat
	public static final String errorMessage = " date must be like " + ISODateTime + " (ex: 2015-10-31T11:51:53.001Z)";

	public static void checkArguments(String fromDate, String toDate) {
		checkArgument(fromDate != null, errorMessage);
		checkArgument(toDate != null, errorMessage);
	}
	
	public static void checkArgument(boolean condition, String errorMessage) {
		if(condition)
			throw new IllegalArgumentException(errorMessage);
	}
	
	public static Timestamp timestamp(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(ISODateTime);
		try {
			return new Timestamp(sdf.parse(date).getTime());
		} catch (ParseException e) {
			throw new IllegalArgumentException(e.getMessage() + errorMessage, e.getCause());
		}
	}

	public static <T> Function<Page<T>, SpringRESTResponse<T>> toSpringRESTResponseFromPage() {
		return new Function<Page<T>, SpringRESTResponse<T>>() {

			@Override
			public SpringRESTResponse<T> apply(Page<T> input) {
				return new SpringRESTResponse<T>( //
						new SpringRESTResponseEmbedded<T>( //
								input.getContent()), //
						new SpringRESTResponsePage( //
								input.getSize(), //
								input.getTotalElements(), //
								input.getTotalPages(), //
								input.getNumber()));
			}
		};
	}

	public static <T> Function<Iterable<T>, SpringRESTResponse<T>> toSpringRESTResponseFromIterable() {
		return new Function<Iterable<T>, SpringRESTResponse<T>>() {
			@Override
			public SpringRESTResponse<T> apply(Iterable<T> input) {
				List<T> list = new ArrayList<>();
				input.forEach(list::add);
				return new SpringRESTResponse<T>( //
						new SpringRESTResponseEmbedded<T>( //
								list), //
						new SpringRESTResponsePage( //
								list.size(), //
								list.size(), //
								1, //
								1));
			}
		};
	}

	/**
	 * Checks if a String is whitespace, empty (""), emptyLike("%%") or null.
	 * <br/>
	 * StringUtils.isBlank(null) = true <br/>
	 * StringUtils.isBlank("") = true <br/>
	 * StringUtils.isBlank(" ") = true <br/>
	 * StringUtils.isBlank("bob") = false <br/>
	 * StringUtils.isBlank("  bob  ") = false <br/>
	 * <br/>
	 * StringUtils.isBlank("%%") = true <br/>
	 * StringUtils.isBlank("% %") = true <br/>
	 * StringUtils.isBlank("%TEST%") = false <br/>
	 * StringUtils.isBlank("% TEST %") = false <br/>
	 * 
	 * @param arg
	 * @return
	 */
	public static boolean areBlank(String... args) {
		if (args == null)
			return true;
		for (String arg : args)
			if (!isBlank(arg))
				return false;
		return true;
	}

	public static boolean areNotBlank(String... args) {
		if (args == null)
			return true;
		for (String arg : args)
			if (isBlank(arg))
				return false;
		return true;
	}

	private static final String EMPTY = "";
	private static final String SPACE = " ";
	private static final String EMPTY_QUERY = "%%";
	private static final String QUERY_BRACKETS = "%";

	public static boolean isBlank(String str) {

		str = (str == null) ? EMPTY : str.replaceAll(SPACE, EMPTY);

		if (str.contains(QUERY_BRACKETS)) {
			if (EMPTY_QUERY.equals(str))
				return true;
			int beginIndex = str.indexOf(QUERY_BRACKETS);
			int endIndex = str.lastIndexOf(QUERY_BRACKETS);
			str = str.substring(beginIndex + 1, endIndex);
		}

		if (StringUtils.isBlank(str)) {
			return true;
		}

		return false;
	}
}
