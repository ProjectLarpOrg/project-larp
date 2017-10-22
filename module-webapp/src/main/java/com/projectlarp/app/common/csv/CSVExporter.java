package com.projectlarp.app.common.csv;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.LazyInitializationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.google.common.base.Function;
import com.google.common.base.Throwables;

public class CSVExporter {

	private static final char SEP = ';';
	private static final char END = '\r';
	private static final char END2 = '\n';
	private static final int CHUNCK_SIZE = 100;
	private static final int EXPORT_MAX = 500000;

	public <T> void exportTo(List<T> items, Appendable writer, String... names) {
		try {
			if (items.isEmpty()) {
				writer.append("");
			} else {
				T obj = items.get(0);
				List<Field> fields = extractFields(obj, names);
				// COLS NAMES
				for (Field f : fields) {
					writer.append(f.getName()+SEP);
				}
				writer.append(END);
				writer.append(END2);
				// ROWS
				for (T i : items) {
					writer = newRow(i, fields, writer);
				}
			}
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}
	
	public void stream(Function<Pageable, Page<?>> function, Sort sort, Appendable writer) throws Exception {
		try {
			Page<?> res = function.apply(new PageRequest(0, 1));
			long totalElements = res.getTotalElements();
			int totalPages = (int) (totalElements / CHUNCK_SIZE);
			if (totalElements == 0) {
				writer.append("empty");
			} else if (totalElements > EXPORT_MAX) {
				writer.append("too big. max " + EXPORT_MAX);
			} else {
				Object obj = res.getContent().get(0);
				List<Field> fields = extractFields(obj);
				// COLS NAMES
				for (Field f : fields) {
					writer.append(f.getName() + SEP);
				}
				writer.append(END);
				writer.append(END2);
				// CHUNKS
				for (int p = 0; p <= totalPages; p++) {
					List<?> items = function.apply(new PageRequest(p, CHUNCK_SIZE, sort)).getContent();
					// ROWS
					for (Object i : items) {
						writer = newRow(i, fields, writer);
					}
				}
			}
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}

	private <T> List<Field> extractFields(T obj, String... names) {
		try {
			List<Field> fields = new ArrayList<Field>();
			if (0 == names.length) {
				fields = extractFieldsAll(obj);
			} else {
				fields = extractFieldsSelect(obj, names);
			}
			return fields;
		} catch (NoSuchFieldException e) {
			throw Throwables.propagate(e);
		}
	}

	private <T> List<Field> extractFieldsAll(T obj) {
		Field[] fieldsAll = obj.getClass().getDeclaredFields();
		List<Field> fields = new ArrayList<Field>();
		for (Field field : fieldsAll) {
			String name = field.getName();
			if (!name.contains("this") && !name.contains("serialVersionUID")) {
				field.setAccessible(true);
				fields.add(field);
			}
		}
		return fields;
	}

	private <T> List<Field> extractFieldsSelect(T obj, String... names) throws NoSuchFieldException {
		List<Field> fields = new ArrayList<Field>();
		for (String name : names) {
			Field field = obj.getClass().getDeclaredField(name);
			field.setAccessible(true);
			fields.add(field);
		}
		return fields;
	}

	/**
	 * WARNING: MODIFY THIS CODE CAREFULLY (OPTIMISATIONS HERE).
	 */
	private <T> Appendable newRow(T obj, List<Field> fields, Appendable writer) {
		try {
			for (int i = 0; i < fields.size(); i++) {
				try {
					writer.append((null == fields.get(i).get(obj)) ? "" : fields.get(i).get(obj).toString());
					writer.append(SEP);
				} catch (LazyInitializationException e) {
					// do nothing
				}
			}
			writer.append(END);
			writer.append(END2);
			return writer;
		} catch (IllegalAccessException e) {
			throw Throwables.propagate(e);
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}

}
