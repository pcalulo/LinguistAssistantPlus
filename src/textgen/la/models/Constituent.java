package textgen.la.models;

import javax.swing.JPanel;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import textgen.la.ui.Box;

public class Constituent {
	private String label, concept, translation;
	private FeatureList featureList;
	private ConstitList constList;
	private int depthLevel;

	JPanel panel;
	Box box;
	
	public Constituent(Node a, int parentDepth, JPanel parentPanel) {
		NamedNodeMap attr = a.getAttributes();
		NodeList children = a.getChildNodes();

		box = new Box();
		
		featureList = new FeatureList();
		depthLevel = parentDepth;
		constList = new ConstitList(box);
		
		panel = parentPanel;

		/*
		 * if (attr.getNamedItem("label").getNodeValue() != null) label =
		 * attr.getNamedItem("label").getNodeValue(); if
		 * (attr.getNamedItem("concept").getNodeValue() != null) concept =
		 * attr.getNamedItem("concept").getNodeValue(); if
		 * (attr.getNamedItem("translation").getNodeValue() != null) translation
		 * = attr.getNamedItem("translation").getNodeValue();
		 */

		for (int i = 0; i < children.getLength(); i++) {
			Node m = children.item(i);

			if (m.getNodeName().equals("label"))
				label = m.getChildNodes().item(0).getNodeValue().trim();
			if (m.getNodeName().equals("concept"))
				concept = m.getChildNodes().item(0).getNodeValue().trim();
			if (m.getNodeName().equals("translation"))
				translation = m.getChildNodes().item(0).getNodeValue().trim();
			if (m.getNodeName().equals("features"))
				featureList.setFeatureNode(m, depthLevel);
			if (m.getNodeName().equals("subconst"))
				constList.setConstitNode(m, depthLevel);
		}
		
		createBox();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public String toXMLString() {
		String depth = "";

		for (int i = 0; i < depthLevel; i++) {
			depth += "\t";
		}

		String toPrint = depth + "<const>\n";

		toPrint += depth + "\t<label>" + label + "</label>\n";

		if (concept != null)
			toPrint += depth + "\t<concept>" + concept + "</concept>\n";
		if (translation != null)
			toPrint += depth + "\t<translation>" + translation
					+ "</translation>\n";

		if (featureList.getLength() != 0)
			toPrint += featureList.toXMLString() + "\n";
		if (constList.getLength() != 0)
			toPrint += constList.toXMLString() + "\n";

		toPrint += depth + "</const>";

		return toPrint;
	}
	
	public void createBox()
	{
		String l;
		
		box.setConstituent(this);
		box.setBackgroundColor(depthLevel);
		panel.add(box);
	}
}
