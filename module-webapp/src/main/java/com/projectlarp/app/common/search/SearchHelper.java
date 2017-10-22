package com.projectlarp.app.common.search;

import static com.google.common.base.Preconditions.checkArgument;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.data.domain.Page;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class SearchHelper {

	public static final String ISODateTime = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"; // ISO8601DateFormat
	public static final String errorMessage = " date must be like " + ISODateTime
			+ " (ex: 2015-10-31T11:51:53.001Z)";
	
	public static void checkArguments(String fromDate, String toDate) {
		checkArgument(fromDate != null, errorMessage);
		checkArgument(toDate != null, errorMessage);
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
				ArrayList<T> list = Lists.newArrayList(input);
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
	 * Checks if a String is whitespace, empty (""), emptyLike("%%") or null. <br/>
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

	public static boolean isBlank(String arg) {
		if (Strings.isNullOrEmpty(arg)) {
			return true;
		} else {
			if (arg.contains("%")) {

				String EMPTY = "%%";
				if (EMPTY.equals(arg))
					return true;

				String BLANK = "%";
				int beginIndex = arg.indexOf(BLANK);
				int endIndex = arg.lastIndexOf(BLANK);
				String substring = arg.substring(beginIndex + 1, endIndex);
				if (Strings.isNullOrEmpty(substring)) {
					return true;
				}
			}
		}
		return false;
	}
}
