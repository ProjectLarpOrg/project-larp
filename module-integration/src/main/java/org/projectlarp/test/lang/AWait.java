package org.projectlarp.test.lang;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableMap;

/**
 * Awaitility is a small Java DSL for synchronizing (waiting for) asynchronous
 * operations. It makes it easy to test asynchronous code. <br>
 * <br>
 * Examples:  <br>
 * <code>
 * AWait.atMost(6 + esbTime, SECONDS);
 * </code>
 */
public class AWait {

	private static Map<TimeUnit, Long> ratio = ImmutableMap.of(SECONDS, 1000L);

	public static void atMost(long sourceDuration, TimeUnit sourceUnit) {
		try {
			Thread.sleep(sourceDuration * ratio.get(sourceUnit));
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
	}
}
