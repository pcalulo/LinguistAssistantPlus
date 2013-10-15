package textgen.la.models;

import java.util.ArrayList;

import javax.swing.JPanel;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Sentence {
	public ArrayList<Constituent> clauses = new ArrayList<Constituent>();
	int depthLevel = 0;
	
	JPanel panel;

	public Sentence() {

	}

	public Sentence(Element root, JPanel parentPanel) {
		NodeList children = root.getChildNodes();
		Node a;

		this.panel = parentPanel;
		
		for (int i = 0; i < children.getLength(); i++) {
			a = children.item(i);

			if (a.getNodeName().equals("const"))
				clauses.add(new Constituent(a, depthLevel, panel));
		}
	}

	/*
	 * public String toString() { String depth = ""; String toPrint =
	 * "<sentence>\n";
	 * 
	 * for (int i = 0; i < depthLevel; i++) { depth += "\t"; }
	 * 
	 * for (int i = 0; i < clauses.size(); i++) { toPrint += depth +
	 * clauses.get(i) + "\n"; }
	 * 
	 * toPrint += "</sentence>";
	 * 
	 * return toPrint; }
	 */

	public String toXMLString() {
		String depth = "";

		for (int i = 0; i < depthLevel; i++) {
			depth += "\t";
		}

		String toPrint = depth + "<sentence>\n";

		for (int i = 0; i < clauses.size(); i++) {
			toPrint += clauses.get(i).toXMLString() + "\n";
		}

		toPrint += depth + "</sentence>";

		return toPrint;
	}
}
