package org.projectlarp.test.util;

import static com.google.common.base.Charsets.UTF_8;
import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

import com.google.common.base.Throwables;
import com.google.common.io.Resources;

/**
 * Custom File laoder.
 */
public class FileLoader {

	public static String read(String filename) {
		checkArgument(isNotBlank(filename));
		try {
			return Resources.toString(FileLoader.class.getClassLoader() //
					.getResource(filename), UTF_8); // REMOVE ALL LINE BREAKS
		} catch (Exception e) {
			throw new RuntimeException("file not found : '" + filename+"'");
		}
	}

	public static void writeLine(File file, String newLine) {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file, true), "UTF-8"))) {
			writer.write(newLine);
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
	}

	public static void clear(File file) {
		try (PrintWriter pw = new PrintWriter(file)) {
			// clear
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
	}
}
