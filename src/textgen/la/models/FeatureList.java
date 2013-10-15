package textgen.la.models;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FeatureList {
	private ArrayList<Feature> features = new ArrayList<Feature>();
	private int length, depthLevel;

	public FeatureList() {
		length = 0;
	}

	public int getLength() {
		return length;
	}
	
	public ArrayList<Feature> getFeatures() {
		return features;
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
}
