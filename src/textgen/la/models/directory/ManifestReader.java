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

		// Get text name
		LinguistText lingText = new LinguistText();

		Node textNode = document.getChildNodes().item(0);
		NamedNodeMap attrs = textNode.getAttributes();

		lingText.setName(attrs.getNamedItem("name").getNodeValue());

		// Get all books. This also handles getting the verses.
		List<Book> books = readBooks(textNode);
		lingText.setBooks(books);

		return lingText;
	}

	/**
	 * Reads the books inside the specified text node. All books that get read
	 * by this function also have the appropriate verses inside each book as
	 * well.
	 * 
	 * @param textNode
	 *            The node representing the top-level text element in a LA+
	 *            manifest file
	 * @return A list of books declared in the manifest file
	 */
	private static List<Book> readBooks(Node textNode) {
		NodeList bookNodes = textNode.getChildNodes();
		List<Book> books = new ArrayList<Book>();

		// For each book...
		for (int i = 0; i < bookNodes.getLength(); i++) {
			Node bookNode = bookNodes.item(i);
			Book book;
			List<VerseReference> verseRefs;
			NamedNodeMap attrs;

			/*
			 * Text nodes contain the stuff between XML tags. We're not putting
			 * anything between XML tags, so we simply ignore these.
			 */
			if (bookNode.getNodeType() == Node.TEXT_NODE) {
				continue;
			}

			// Create the book and get its metadata
			book = new Book();
			attrs = bookNode.getAttributes();
			book.setName(attrs.getNamedItem("name").getNodeValue());
			book.setId(Integer
					.parseInt(attrs.getNamedItem("id").getNodeValue()));

			// Get the verses in this book
			verseRefs = readVerses(bookNode);
			book.setVerseReferences(verseRefs);
			
			// and lastly, put it in the list
			books.add(book);
		}

		return books;
	}

	/**
	 * Reads the verses inside the specified Node, and returns them in a nice
	 * convenient List. This function assumes that bookNode really is a book
	 * node.
	 * 
	 * @param bookNode
	 *            The book node to read verses from
	 * @return A list of verses inside the specified node
	 */
	private static List<VerseReference> readVerses(Node bookNode) {
		List<VerseReference> verseRefs = new ArrayList<>();
		NodeList verseNodes = bookNode.getChildNodes();

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
