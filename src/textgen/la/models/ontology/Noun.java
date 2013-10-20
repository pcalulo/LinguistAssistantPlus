package textgen.la.models.ontology;

public class Noun {
	private String name, value,class1;
	
	public Noun(String name, String val, String class1)
	{
		this.name = name;
		this.value = val;
		this.class1 = class1;
		
	}
	
	public String getName()
	{
		return name;
	}
	public String getValue()
	{
		return value;
	}
	
	public String getClass1()
	{
		return class1;
	}
	
}
