package textgen.la.models;

import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.FileWriter;

public class Parser {
	DocumentBuilderFactory factory;
	DocumentBuilder builder;
	Document docu;

	JPanel panel;
	
	public Sentence readXMLFile(String bookName, String sentenceNo) {
		try {
			String fileName = bookName + "_" + sentenceNo + ".xml";

			File xmlFile = new File("resources/"+fileName);
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			docu = builder.parse(xmlFile);
			docu.getDocumentElement().normalize();
			Element root = docu.getDocumentElement(); // root

			return new Sentence(root, panel);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void writeToFile(String bookName, String sentenceNo, Sentence a) {
		String fileName = bookName + "_" + sentenceNo + ".xml";

		File file = new File(fileName);
		try {
			FileWriter fw = new FileWriter(file);
			if (!file.createNewFile())
				; // file already exists, confirm changes
			fw.write(a.toXMLString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Parser(JPanel panel)
	{
		this.panel = panel;
		Sentence a = readXMLFile("InfectedEye", "1");
		System.out.println(a.toXMLString());
		writeToFile("InfectedEye", "1", a);

	}
}
