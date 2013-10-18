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
	
	public Constituent(String label,String concept, String translation, FeatureList featureList, ConstitList constList){
		this.label=label;
		this.concept=concept;
		this.translation=translation;
		this.featureList=featureList;
		this.constList=constList;
	}
	
	public Constituent(Node a, int parentDepth) {
		NamedNodeMap attr = a.getAttributes();
		NodeList children = a.getChildNodes();

		featureList = new FeatureList();
		depthLevel = parentDepth;
		constList = new ConstitList();

		/*
		 * if (attr.getNamedItem("label").getNodeValue() != null) label =
		 * attr.getNamedItem("label").getNodeValue(); if
		 * (attr.getNamedItem("concept").getNodeValue() != null) concept =
		 * attr.getNamedItem("concept").getNodeValue(); if
		 * (attr.getNamedItem("translation").getNodeValue() != null) translation
		 * = attr.getNamedItem("translation").getNodeValue();
		 */

		for (int i = 0; i < children.getLength(); i++) {
			Node node = children.item(i);

			if (node.getNodeName().equals("label"))
				label = node.getChildNodes().item(0).getNodeValue().trim();
			if (node.getNodeName().equals("concept"))
				concept = node.getChildNodes().item(0).getNodeValue().trim();
			if (node.getNodeName().equals("translation"))
				translation = node.getChildNodes().item(0).getNodeValue().trim();
			if (node.getNodeName().equals("features"))
				featureList.setFeatureNode(node, depthLevel);
			if (node.getNodeName().equals("subconst"))
				constList.setConstitNode(node, depthLevel);
		}
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

	public FeatureList getFeatureList() {
		return featureList;
	}

	public ConstitList getConstList() {
		return constList;
	}
	
	public int getDepthLevel()
	{
		return depthLevel;
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

//	public void createBox() {
//		box.setConstituent(this);
//		box.setBackgroundColor(depthLevel);
//		parentPanel.add(box);
//	}
//	
//	public void recreateBox(JPanel newParent) {
//		parentPanel = newParent;
//		createBox();
//		
//		this.constList.recreateBoxes(box);
//	}
}
