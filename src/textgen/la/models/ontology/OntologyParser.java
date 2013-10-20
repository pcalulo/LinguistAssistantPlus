package textgen.la.models.ontology;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OntologyParser {
	DocumentBuilderFactory factory;
	DocumentBuilder builder;
	Document docu;
	Ontology ontologies = new Ontology();
	
	public void readXMLFile(String filename)
	{
		try{
				File xml = new File(filename);
				factory = DocumentBuilderFactory.newInstance();
				builder = factory.newDocumentBuilder();
				docu = builder.parse(xml);
				docu.getDocumentElement().normalize();
				Element root = docu.getDocumentElement();
				
				System.out.println("<"+root.getNodeName()+">");
				NodeList n = root.getChildNodes();
				
				for(int i = 0; i< n.getLength(); i++)
				{
					Node m = n.item(i);
					if(m.getNodeName().equals("adjectives"))
						getAttributes((Element)m);
					if(m.getNodeName().equals("adverbs"))
						getAttributes((Element)m);
					if(m.getNodeName().equals("particles"))
						getAttributes((Element)m);
					if(m.getNodeName().equals("verbs"))
						getAttributes((Element)m);
					if(m.getNodeName().equals("conjunctions"))
						getAttributes((Element)m);
					if(m.getNodeName().equals("relations"))
						getAttributes((Element)m);
					if(m.getNodeName().equals("nouns"))
						getAttributes((Element)m);
				}
				System.out.println("</"+root.getNodeName()+">");
				}catch(Exception e)
				{
					e.printStackTrace();
				}
	}
	public void getAttributes(Element a)
	{
		String name = null;
		NodeList n = a.getChildNodes();
		Attr attribute;
		
		System.out.println("\t<"+a.getNodeName()+">");
		
		for (int i = 0; i < n.getLength(); i++) {
            Node m = n.item(i);
            
           if(m.getNodeType() == Node.ELEMENT_NODE) 
            {
        	   	System.out.print("\t\t<"+m.getNodeName()+" ");
        	   	attribute = (Attr) m.getAttributes().getNamedItem("name");
            	name =  attribute.getValue();
            	System.out.print(attribute.getName()+" = " +"'"+name+"'>");
                
            	NodeList children = m.getChildNodes();
    	    	for (int j = 0; j < children.getLength(); j++) {
    	            Node x = children.item(j);
    	            getAttribValue(x,a,name);
    	    	}
            	System.out.print("</"+m.getNodeName()+">\n");
            }
		}
		 System.out.println("\t</"+a.getNodeName()+">");
	}
	public void getAttribValue(Node x,Element a,String name)
	{
		String value = null;
		String class1 = null; 
		
		if(x.getNodeType() == Node.ELEMENT_NODE) 
        {
        	value = x.getTextContent();
          	if(x.getNodeName().equals("class")) 
          		class1 = x.getTextContent();
          	
          	System.out.print("<"+x.getNodeName()+">"+value);
          	System.out.print("</"+x.getNodeName()+">");
          	
         	if(a.getNodeName().equals("adjectives"))
	   			  ontologies.adjList.add(new POS(name,value));
        	if(a.getNodeName().equals("adverbs"))
	   			  ontologies.advList.add(new POS(name,value));
        	if(a.getNodeName().equals("conjunctions"))
	   			  ontologies.conjList.add(new POS(name,value));
        	if(a.getNodeName().equals("nouns"))
	   			  ontologies.nounList.add(new POS(name,value,class1));
        	if(a.getNodeName().equals("particles"))
	   			  ontologies.pList.add(new POS(name,value));
        	if(a.getNodeName().equals("relatiions"))
	   			  ontologies.rList.add(new POS(name,value));
        	if(a.getNodeName().equals("verbs"))
	   			  ontologies.verbList.add(new POS(name,value));
        }
	}
	public OntologyParser()
	{
		readXMLFile("resources/Ontology_1.xml");
	}
	
	public Ontology getOntology()
	{
		return ontologies;
	}
}
