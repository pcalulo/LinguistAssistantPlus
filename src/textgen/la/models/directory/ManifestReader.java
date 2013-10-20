package textgen.la.models.directory;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import textgen.la.models.LinguistText;

public class ManifestReader {

	public static LinguistText read(File file) throws IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		Document document = null;

		try {
			db = dbf.newDocumentBuilder();
			document = db.parse(file);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		document.getDocumentElement().normalize();
		// readNodes(document.getChildNodes(), 0);

		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(System.out);
			transformer.transform(source, result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	private static void readNodes(NodeList nodes, int level) {
		String indent = "";

		for (int i = 0; i < level; i++) {
			indent += "    ";
		}

		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			int type = node.getNodeType();

			if (type != Node.TEXT_NODE) {
				System.out.println(indent + node.getNodeName() + "  : " + type);
				readNodes(node.getChildNodes(), level + 1);
			}
		}
	}

	public static void main(String[] args) {
		File file = new File("resources/InfectedEyeSample/manifest.xml");
		try {
			read(file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
