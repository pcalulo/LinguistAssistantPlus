package textgen.la.models;

import javax.swing.JPanel;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import textgen.la.ui.Box;

public class Constituent {
	private String label, concept, translation;
	private FeatureList featureList;
	private ConstitList constList;
	private int depthLevel;
	
	public Constituent(String label, String concept, String translation, int parentDepth){
		this.label=label;
		this.concept=concept;
		this.translation=translation;
		
		depthLevel = parentDepth+1;
		
		featureList = new FeatureList();
		constList = new ConstitList();
	}
	
	public Constituent(String label, String concept, String translation, FeatureList features, ConstitList subconstits, int parentDepth){
		this.label=label;
		this.concept=concept;
		this.translation=translation;

		depthLevel = parentDepth+1;

		featureList = features;
		constList = subconstits;
	}

	// OPERATIONS
	public void addFeature(Feature f) {
		featureList.addFeature(f);
	}
	
	public FeatureList getFeatureList() {
		return featureList;
	}
	
	public void addSubConstit(Constituent c, int index) {
		constList.insertConstituent(c, index);
	}
	
	public ConstitList getConstitList() {
		return constList;
	}
	
	// XML STUFF
	public Constituent(Element element, int parentDepth) {
		//NamedNodeMap attr = a.getAttributes();
		//NodeList children = a.getChildNodes();

		featureList = new FeatureList();
		depthLevel = parentDepth+1;
		constList = new ConstitList();
		Attr attr;
		NamedNodeMap attributes;
		
		/*
		 * if (attr.getNamedItem("label").getNodeValue() != null) label =
		 * attr.getNamedItem("label").getNodeValue(); if
		 * (attr.getNamedItem("concept").getNodeValue() != null) concept =
		 * attr.getNamedItem("concept").getNodeValue(); if
		 * (attr.getNamedItem("translation").getNodeValue() != null) translation
		 * = attr.getNamedItem("translation").getNodeValue();
		 */
		if(element.hasAttributes())
		{
			attributes = element.getAttributes();
			attr = (Attr) attributes.getNamedItem("label");
			if(attr != null) {
				label = attr.getValue();
			}
			
			attr = (Attr) attributes.getNamedItem("concept");
			if (attr != null) {
				setConcept(attr.getValue());
				
				attr = (Attr) attributes.getNamedItem("sense");
				if (attr != null) {
					setConcept(getConcept() + "-" +  attr.getValue());
				}
			}
		}
		
		NodeList children= element.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node node = children.item(i);

			/*if (node.getNodeName().equals("label"))
				label = node.getChildNodes().item(0).getNodeValue().trim();*/
			if (node.getNodeName().equals("concept"))
				concept = node.getChildNodes().item(0).getNodeValue().trim();
			if (node.getNodeName().equals("translation"))
				translation = node.getChildNodes().item(0).getNodeValue().trim();
			if (node.getNodeName().equals("features"))
				featureList.setFeatureNode((Element)node, depthLevel);
			if (node.getNodeName().equals("subconst"))
				constList.setConstitNode((Element)node, depthLevel);
		}
	}
	
	public String toXMLString() {
		String depth = "";

		for (int i = 0; i < depthLevel; i++) {
			depth += "\t";
		}

		String toPrint = depth + "<const ";
		
		toPrint +=  "label = \""+ label + "\">\n";
		
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
	
	//SETTERS
	public void setLabel(String l) {
		label = l;
	}
	
	public void setConcept(String c) {
		concept = c;
	}
	
	public void setTranslation(String t) {
		translation = t;
	}
	
	//GETTERS
	public String getLabel() {
		return label;
	}

	public String getConcept() {
		return concept;
	}

	public String getTranslation() {
		return translation;
	}
	
	public int getDepthLevel()
	{
		return depthLevel;
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
