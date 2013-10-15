package textgen.la.models;

import java.util.ArrayList;

import javax.swing.JPanel;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConstitList {
	ArrayList<Constituent> cons = new ArrayList<Constituent>();
	int length, depthLevel;

	JPanel panel;
	
	public ConstitList(JPanel parentPanel) {
		length = 0;
		this.panel = parentPanel;
	}

	public void setConstitNode(Node m, int parentDepth) {
		NodeList children = m.getChildNodes();
		Node a;

		depthLevel = parentDepth + 1;

		for (int i = 0; i < children.getLength(); i++) {
			a = children.item(i);

			if (a.getNodeName().equals("const")) {
				cons.add(new Constituent(a, depthLevel, panel));
				length++;
			}
		}
	}

	public int getLength() {
		return length;
	}

	public String toXMLString() {
		String depth = "";

		for (int i = 0; i < depthLevel; i++)
			depth += "\t";

		if (length == 0)
			return "\n";

		String toPrint = depth + "<subconst>\n";

		for (int i = 0; i < length; i++) {
			toPrint += cons.get(i).toXMLString() + "\n";
		}

		toPrint += depth + "</subconst>";

		return toPrint;
	}
}