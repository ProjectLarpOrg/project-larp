package org.projectlarp.test.util;

import static com.google.common.base.Preconditions.checkState;

import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.util.Base64;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.google.common.base.Throwables;

/**
 * Custom HTTP connection manager.
 */
public class HTTPConnection {

	public static int getHTTPResponseCodeFrom(String stringUrl) {
		try {
			URL url = new URL(stringUrl);
			System.out.println(stringUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			int code = connection.getResponseCode();
			System.out.println(code);
			connection.disconnect();
			return code;
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
	}

	public static String getHTTPResponseMessageFrom(String stringUrl) {
		try {
			URL url = new URL(stringUrl);
			System.out.println(stringUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			String message = String.valueOf(connection.getContent());
			System.out.println(message);
			connection.disconnect();
			return message;
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
	}

	public static String postReturningMessage(String stringUrl) {
		return httpRequestResponseMessage(stringUrl, HttpMethod.POST);
	}

	public static String getReturningMessage(String stringUrl) {
		return httpRequestResponseMessage(stringUrl, HttpMethod.GET);
	}

	public static int postReturningCode(String stringUrl) {
		return httpRequestResponseCode(stringUrl, HttpMethod.POST);
	}

	public static int getReturningCode(String stringUrl) {
		return httpRequestResponseCode(stringUrl, HttpMethod.GET);
	}

	public static int postReturningCode(String stringUrl, String user,
			String password) {
		return httpRequestResponseCode(stringUrl, HttpMethod.POST, user,
				password);
	}

	public static String getReturningJSonMessageOk(String stringUrl,
			String user, String password) {
		return httpRequestResponseMessage(stringUrl, HttpMethod.GET, 200,
				"application/json", user, password);
	}

	private static String httpRequestResponseMessage(String stringUrl,
			HttpMethod method) {
		try {
			URL url = new URL(stringUrl);
			System.out.println(stringUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod(method.toString());
			connection.connect();
			String message = String.valueOf(connection.getContent());
			System.out.println(message);
			connection.disconnect();
			return message;
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
	}

	private static String httpRequestResponseMessage(String stringUrl,
			HttpMethod method, int expectedResponseCode, String contentType,
			String user, String password) {
		try {

			URL url = new URL(stringUrl);
			System.out.println(stringUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			if (contentType != null) {
				connection.addRequestProperty("Content-Type", contentType);
				connection.addRequestProperty("Accept", contentType);
			}
			if (user != null && password != null) {
				addBasicCredentials(connection, user, password);
			}
			connection.setRequestMethod(method.toString());
			connection.connect();
			int code = connection.getResponseCode();
			System.out.println(code);
			checkState(code == expectedResponseCode);
			System.out.println(connection.getContentType());
			InputStream is = connection.getInputStream();
			StringWriter sw = new StringWriter();
			IOUtils.copy(is, sw);
			String message = sw.toString();;
			System.out.println(message);
			connection.disconnect();
			return message;
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
	}

	private static int httpRequestResponseCode(String stringUrl,
			HttpMethod method) {
		try {
			URL url = new URL(stringUrl);
			System.out.println(stringUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod(method.toString());
			connection.connect();
			int code = connection.getResponseCode();
			System.out.println(code);
			connection.disconnect();
			return code;
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
	}

	private static int httpRequestResponseCode(String stringUrl,
			HttpMethod method, String user, String password) {
		try {
			URL url = new URL(stringUrl);
			System.out.println(stringUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			addBasicCredentials(connection, user, password);
			connection.setRequestMethod(method.toString());
			connection.connect();
			int code = connection.getResponseCode();
			System.out.println(code);
			connection.disconnect();
			return code;
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
	}

	private static void addBasicCredentials(HttpURLConnection connection,
			String user, String password) throws UnsupportedEncodingException {
		String auth = Base64.encodeBase64String((user + ":" + password)
				.getBytes("UTF-8"));
		auth = auth.replaceAll("\n", "");
		connection.addRequestProperty("Authorization", "Basic " + auth);
	}

}
