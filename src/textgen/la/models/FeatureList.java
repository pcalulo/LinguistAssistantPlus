package textgen.la.models;

import java.util.ArrayList;
import java.util.Collections;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FeatureList {
	private ArrayList<Feature> features = new ArrayList<Feature>();
	private int length, depthLevel;

	public FeatureList(ArrayList<Feature> features){
		this.features=features;
	}
	
	public FeatureList() {
		length = 0;
	}

	public int getLength() {
		return length;
	}
	
	public Feature getFeature(int index) {
		if (index < length)
			return features.get(index);
		return null;
	}
	
	public void addFeature(Feature f) {
		features.add(f);
		
		Collections.sort(features);
	}
	
	public void removeFeature(Feature f) {
		// removes first feature na same name with this one
		for (int i = 0; i < length; i++) {
			if (f.equals(features.get(i))) {
				features.remove(i);
				return;
			}
		}
	}

	public void removeFeature(int index) {
		features.remove(index);
	}

	public void setFeatureNode(Node m, int parentDepth) {
		NodeList children = m.getChildNodes();
		Node a;

		depthLevel = parentDepth + 1;

		for (int i = 0; i < children.getLength(); i++) {
			a = children.item(i);

			if (a.getNodeName().equals("feature")) {
				features.add(new Feature(a, depthLevel));
				length++;
			}
		}
	}

	public String toXMLString() {
		String depth = "";

		for (int i = 0; i < depthLevel; i++) {
			depth += "\t";
		}

		if (length == 0)
			return "\n";

		String toPrint = depth + "<features>\n";

		for (int i = 0; i < length; i++) {
			toPrint += features.get(i).toXMLString() + "\n";
		}

		toPrint += depth + "</features>";

		return toPrint;
	}

	public ArrayList<Feature> getFeatures() {
		return features;
	}
}
