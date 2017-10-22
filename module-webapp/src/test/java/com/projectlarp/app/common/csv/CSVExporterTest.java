package com.projectlarp.app.common.csv;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.junit.Ignore;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.projectlarp.app.common.csv.CSVExporter;

public class CSVExporterTest {


	<T> String exportToString(List<T> items, String... names) {
		CSVExporter classToTest = new CSVExporter();
		StringBuilder writer = new StringBuilder();
		classToTest.exportTo(items, writer, names);
		return writer.toString();
	}
	
	@Test
	public void testExport() {
		// GIVEN
		List<TestObject> args = ImmutableList.of( //
				new TestObject("val1", "val2", "val3"), //
				new TestObject("val4", "val5", "val6"), //
				new TestObject("val7", "val8", "val9"));
		// WHEN
		String result = null;
		result = exportToString(args);
		// THEN
		assertThat(result).contains("\r\n");
		assertThat(result.split("\r\n")).hasSize(args.size() + 1);
		assertThat(result).contains("field1;field2;field3");
		assertThat(result).contains("val1;val2;val3");
		assertThat(result).contains("val4;val5;val6");
		assertThat(result).contains("val7;val8;val9");
	}

	@Test
	public void testExportNames() {
		// GIVEN
		List<TestObject> args = ImmutableList.of( //
				new TestObject("val1", "val2", "val3"), //
				new TestObject("val4", "val5", "val6"), //
				new TestObject("val7", "val8", "val9"));
		// WHEN
		String result = null;
		result = exportToString(args, "field1", "field2");
		// THEN
		assertThat(result).contains("\r\n");
		assertThat(result.split("\r\n")).hasSize(args.size() + 1);
		assertThat(result).contains("field1;field2");
		assertThat(result).contains("val1;val2");
		assertThat(result).contains("val4;val5");
		assertThat(result).contains("val7;val8");
	}

	@Test
	public void testOrder() {
		// GIVEN
		List<TestObject> args = ImmutableList.of( //
				new TestObject("val1", "val2", "val3"), //
				new TestObject("val4", "val5", "val6"), //
				new TestObject("val7", "val8", "val9"));
		// WHEN
		String result = null;
		result = exportToString(args, "field3", "field2", "field1");
		// THEN
		assertThat(result).contains("\r\n");
		assertThat(result.split("\r\n")).hasSize(args.size() + 1);
		assertThat(result).contains("val3;val2;val1");
	}

	@Data
	@AllArgsConstructor
	class TestObject {
		String field1;
		String field2;
		String field3;
	}

	// REMOVE @Ignore FOR PERF TESTS: expected 2-3sec max for perfSize = 1 *
	// 1000 * 1000.
	@Ignore
	@Test
	public void testPerformance() {
		// GIVEN
		List<TesTPerfObject> args = new ArrayList<TesTPerfObject>();
		int perfSize = 1 * 1000 * 1000;
		for (int i = 0; i < perfSize; i++) {
			args.add(new TesTPerfObject("1", "2", "3", "4", "5", "6", "7", "8",
					"9", "10", "11", "12", "13"));
		}

		// WHEN
		String result = exportToString(args, "field1", "field2",
				"field3", "field4", "field5", "field6", "field7", "field8",
				"field9", "field10", "field11", "field12", "field13");
		// THEN
		assertThat(result).contains("\r\n");
		assertThat(result.split("\r\n")).hasSize(args.size());
		assertThat(result).contains("1;2;3;4;5;6;7;8;9;10;11;12;13");
	}

	@Data
	@AllArgsConstructor
	class TesTPerfObject {
		String field1;
		String field2;
		String field3;
		String field4;
		String field5;
		String field6;
		String field7;
		String field8;
		String field9;
		String field10;
		String field11;
		String field12;
		String field13;
	}

}
