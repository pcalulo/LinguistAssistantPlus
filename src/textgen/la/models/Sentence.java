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

	public Sentence(Element root) {
		NodeList children = root.getChildNodes();
		Node node;

		for (int i = 0; i < children.getLength(); i++) {
			node = children.item(i);

			if (node.getNodeName().equals("const"))
				clauses.add(new Constituent((Element) node, depthLevel));
		}
	}

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
	
	public ArrayList<Constituent> getClauses()
	{
		return clauses;
	}
	
//	public JPanel toBoxes() {
//		JPanel panel = new JPanel();
//		
//		for (Constituent clause : clauses) {
//			clause.recreateBox(panel);
//		}
//		
//		return panel;
//	}
}
