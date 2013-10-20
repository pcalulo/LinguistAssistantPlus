package textgen.la.models.ontology;

public class POS {

	String name, value, oClass;
	
	public POS (String name, String val) {
		this.name = name;
		this.value = val;
	}
	
	public POS (String name, String val, String oClass) {
		this.name = name;
		this.value = val;
		this.oClass = oClass;
	}
	
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	public String getValue() {
		// TODO Auto-generated method stub
		return value;
	}
}
