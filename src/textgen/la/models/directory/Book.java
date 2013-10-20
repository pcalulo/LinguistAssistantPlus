package textgen.la.models.directory;

import java.util.ArrayList;
import java.util.List;

public class Book {
	private String name;
	private int id;
	private List<VerseReference> verseRefs;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Book() {
		verseRefs = new ArrayList<VerseReference>();
	}

	public List<VerseReference> getVerseReferences() {
		return verseRefs;
	}

	public void setVerseReferences(List<VerseReference> verseRefs) {
		this.verseRefs = verseRefs;
	}

	public void addVerseReference(VerseReference verseRef) {
		verseRefs.add(verseRef);
	}

	public void removeVerseReference(VerseReference verseRef) {
		verseRefs.remove(verseRef);
	}
	
	public void printContents() {
		System.out.println("Book: " + getName() + " | ID: " + getId());
		System.out.println("Verse References: ");
		
		for (VerseReference verseRef : verseRefs) {
			verseRef.printContents();
		}
	}
}
