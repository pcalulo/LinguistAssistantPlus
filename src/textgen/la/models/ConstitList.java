package textgen.la.models;

import java.util.ArrayList;

import javax.swing.JPanel;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConstitList {
	ArrayList<Constituent> cons = new ArrayList<Constituent>();
	int length, depthLevel;
	
	public ConstitList() {
		length = 0;
	}

	public void setConstitNode(Element m, int parentDepth) {
		NodeList children = m.getElementsByTagName("const");
		Element a;

		depthLevel = parentDepth + 1;

		for (int i = 0; i < children.getLength(); i++) {
			a = (Element) children.item(i);

			if (a.getNodeName().equals("const")) {
				cons.add(new Constituent(a, depthLevel));
				length++;
			}
		}
	}

	public int getLength() {
		return length;
	}
	
	public void addConstituentToTail(Constituent c) {
		cons.add(c);
	}
	
	public void insertConstituent(Constituent c, int index) {
		cons.add(index, c);
	}
	
	public void removeConstit(int index) {
		cons.remove(index);
	}
	
	public Constituent getConstit(int index) {
		return cons.get(index);
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
	
//	public void recreateBoxes(JPanel newParent) {
//		for (Constituent constit : cons) {
//			constit.recreateBox(newParent);
//		}
//	}
}