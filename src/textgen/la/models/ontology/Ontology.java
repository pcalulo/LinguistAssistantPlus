package textgen.la.models.ontology;

import java.util.ArrayList;

public class Ontology {
	//public ArrayList<Constituent> clauses = new ArrayList<Constituent>();
	ArrayList <POS> adjList = new ArrayList<POS>();
	ArrayList <POS> advList = new ArrayList<POS>();
	ArrayList <POS> conjList = new ArrayList<POS>();
	ArrayList <POS> nounList = new ArrayList<POS>();
	ArrayList <POS> pList = new ArrayList<POS>();
	ArrayList <POS> rList = new ArrayList<POS>();
	ArrayList <POS> verbList = new ArrayList<POS>();
	
	public ArrayList<POS> getAdjList()
	{
		return adjList;
	}
	
	public ArrayList<POS> getAdvList()
	{
		return advList;
	}
	
	public ArrayList<POS> getConjList()
	{
		return conjList;
	}
	
	public ArrayList<POS> getNounList()
	{
		return nounList;
	}
	
	public ArrayList<POS> getPList()
	{
		return pList;
	}
	
	public ArrayList<POS> getRList()
	{
		return rList;
	}
	
	public ArrayList<POS> getVerbList()
	{
		return verbList;
	}
}
