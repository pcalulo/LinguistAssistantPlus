package textgen.la.models;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Feature implements Comparable <Feature> {
	private String name, value;
	private int depthLevel;

	public Feature(String n, String val) {
		name = n;
		value = val;
	}
	
	public Feature(Node a, int parentDepth) {
		NamedNodeMap attr = a.getAttributes();
		NodeList m = a.getChildNodes();

		depthLevel = parentDepth + 1;

		/*
		 * if (attr.getNamedItem("name").getNodeValue() != null) name =
		 * attr.getNamedItem("name").getNodeValue(); if
		 * (attr.getNamedItem("value").getNodeValue() != null) value =
		 * attr.getNamedItem("value").getNodeValue();
		 */
		for (int i = 0; i < m.getLength(); i++) {
			Node n = m.item(i);

			if (n.getNodeName().equals("name"))
				name = n.getChildNodes().item(0).getNodeValue().trim();
			if (n.getNodeName().equals("value"))
				value = n.getChildNodes().item(0).getNodeValue().trim();
		}
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
	
	public void setName(String newName) {
		name = newName;
	}

	public void setValue(String newValue) {
		value= newValue;
	}

	public String toXMLString() {
		String depth = "";

		for (int i = 0; i < depthLevel; i++) {
			depth += "\t";
		}

		String toPrint = depth + "<feature>\n";

		toPrint += depth + "\t<name>" + name + "</name>\n";
		toPrint += depth + "\t<value>" + value + "</value>\n";

		return toPrint + depth + "</feature>";
	}

	public int compareTo(Feature f) {
		return this.name.compareTo(f.name);
	}
}
