package textgen.la.models.directory;

public class VerseReference {
	private String fileName;
	private int id;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void printContents() {
		System.out.println("VerseReference: " + getFileName() + " | ID: " + getId());
	}
}
