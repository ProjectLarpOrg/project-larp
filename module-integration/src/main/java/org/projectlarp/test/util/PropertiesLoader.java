package org.projectlarp.test.util;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Properties;

import com.google.common.base.Throwables;

/**
 * Custom Properties loader providing sub key filer.
 */
public class PropertiesLoader {

	private Properties props;

	public PropertiesLoader(String resourceName) {
		props = load(resourceName);
	}

	public Properties of(String prefix) {
		return startWith(props, prefix);
	}

	private Properties load(String resourceName) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try (InputStream resourceStream = loader
				.getResourceAsStream(resourceName)) {
			Properties props = new Properties();
			props.load(resourceStream);
			return props;
		} catch (Exception e) {
			throw new RuntimeException("Unable to load properties resource="
					+ resourceName);
		}
	}

	private Properties startWith(Properties source, String prefix) {
		Properties target = new Properties();
		final Iterator<Object> keyIterator = source.keySet().iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next().toString();
			if (key.startsWith(prefix)) {
				String newKey = key.replace(prefix, "");
				newKey = newKey.startsWith(".") ? newKey.replaceFirst(".", "")
						: newKey;
				target.put(newKey, source.getProperty(key));
			}
		}
		return target;
	}

	// WRAPPER
	public String getProperty(String key) {
		return props.getProperty(key);
	}

	public String getProperty(String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	}

	@SuppressWarnings({ "unchecked" })
	public <T> T getProperty(String key, Class<T> clazz) {
		try {
			String property = props.getProperty(key);
			Method method = clazz.getMethod("valueOf", String.class);
			if (method == null)
				throw new IllegalArgumentException("unsupported clazz type!");
			return (T) method.invoke(null, property);
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
	}

	public <T> T getProperty(String key, String defaultValue, Class<T> clazz) {
		return getProperty(key, clazz);
	}

}
