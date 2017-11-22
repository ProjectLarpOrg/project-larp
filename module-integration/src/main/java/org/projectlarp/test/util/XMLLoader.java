package org.projectlarp.test.util;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

import com.google.common.base.Throwables;

public class XMLLoader {

	public static String evaluateXML(String xml, String expression) {
		System.out.println("evaluateXML expression =" + expression);
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new ByteArrayInputStream(xml
					.getBytes()));
			XPath xPath = XPathFactory.newInstance().newXPath();
			return xPath.compile(expression).evaluate(doc);
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
	}
}
