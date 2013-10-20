package textgen.la.models.directory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * 
 * ManifestReader is responsible for interpreting the LA+ manifest file format,
 * and instantiating the corresponding objects.
 * 
 * @author Lawrence Patrick Calulo
 * 
 */
public class ManifestReader {

	/**
	 * The file name that all manifest files *MUST* have.
	 */
	public static final String MANIFEST_FILE_NAME = "manifest.xml";

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

		// Create the LinguistText, passing in its parent directory
		// VerseReferences will use this directory as a starting point to load
		// verse XML files.
		LinguistText lingText = new LinguistText(file.getParentFile());

		Node textNode = document.getChildNodes().item(0);
		NamedNodeMap attrs = textNode.getAttributes();

		lingText.setName(attrs.getNamedItem("name").getNodeValue());

		// Get all chapters. This also handles getting the verses.
		List<Chapter> chapters = readChapters(textNode);
		lingText.setChapters(chapters);

		return lingText;
	}

	/**
	 * Reads the chapters inside the specified text node. All chapters that get
	 * read by this function also have the appropriate verses inside each
	 * chapter as well.
	 * 
	 * @param textNode
	 *            The node representing the top-level text element in a LA+
	 *            manifest file
	 * @return A list of chapters declared in the manifest file
	 */
	private static List<Chapter> readChapters(Node textNode) {
		NodeList chapterNodes = textNode.getChildNodes();
		List<Chapter> chapters = new ArrayList<Chapter>();

		// For each chapter...
		for (int i = 0; i < chapterNodes.getLength(); i++) {
			Node chapterNode = chapterNodes.item(i);
			Chapter chapter;
			List<VerseReference> verseRefs;
			NamedNodeMap attrs;

			/*
			 * Text nodes contain the stuff between XML tags. We're not putting
			 * anything between XML tags, so we simply ignore these.
			 */
			if (chapterNode.getNodeType() == Node.TEXT_NODE) {
				continue;
			}

			// Create the chapter and get its metadata
			chapter = new Chapter();
			attrs = chapterNode.getAttributes();
			chapter.setName(attrs.getNamedItem("name").getNodeValue());
			chapter.setId(Integer.parseInt(attrs.getNamedItem("id")
					.getNodeValue()));

			// Get the verses in this chapter
			verseRefs = readVerses(chapterNode);
			chapter.setVerseReferences(verseRefs);

			// and lastly, put it in the list
			chapters.add(chapter);
		}

		return chapters;
	}

	/**
	 * Reads the verses inside the specified Node, and returns them in a nice
	 * convenient List. This function assumes that chapterNode really is a
	 * chapter node.
	 * 
	 * @param chapterNode
	 *            The chapter node to read verses from
	 * @return A list of verses inside the specified node
	 */
	private static List<VerseReference> readVerses(Node chapterNode) {
		List<VerseReference> verseRefs = new ArrayList<>();
		NodeList verseNodes = chapterNode.getChildNodes();

		for (int i = 0; i < verseNodes.getLength(); i++) {
			Node verseNode = verseNodes.item(i);

			// Again, ignore text nodes. This is another way of doing it.
			if (verseNode instanceof Text) {
				continue;
			}

			VerseReference verseRef = new VerseReference();
			NamedNodeMap attrs = verseNode.getAttributes();
			verseRef.setFileName(attrs.getNamedItem("filename").getNodeValue());

			String idStr = attrs.getNamedItem("id").getNodeValue();
			verseRef.setId(Integer.parseInt(idStr));

			verseRefs.add(verseRef);
		}

		return verseRefs;
	}

	/**
	 * Utility function for debugging/information. This recursively prints out
	 * information about a tree of nodes.
	 * 
	 * @param nodes
	 *            The node to print out
	 * @param level
	 *            The level of recursion we're running at
	 */
	private static void printNodes(NodeList nodes, int level) {
		String indent = "";

		for (int i = 0; i < level; i++) {
			indent += "    ";
		}

		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			int type = node.getNodeType();

			if (type != Node.TEXT_NODE) {
				System.out.println(indent + node.getNodeName() + "  : " + type);
				printNodes(node.getChildNodes(), level + 1);
			}
		}
	}

	/**
	 * Utility function for debugging/information. This simply calls the actual
	 * printNodes function with a level of 0, to get the recursion started.
	 * 
	 * @param nodes
	 *            The node to print out
	 */
	@SuppressWarnings("unused")
	private static void printNodes(NodeList nodes) {
		printNodes(nodes, 0);
	}

	public static void main(String[] args) {
		File file = new File("resources/InfectedEyeSample/manifest.xml");
		try {
			LinguistText linguistText = read(file);
			linguistText.printContents();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
