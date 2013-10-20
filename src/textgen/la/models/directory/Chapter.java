package textgen.la.models.directory;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.TreeNode;

/**
 * Chapter represents a chapter declared in the manifest file. It holds all
 * corresponding VerseReferences. For convenience, it implements the TreeNode
 * interface, allowing it to be more easily displayed in JTree widgets.
 * 
 * @author Lawrence Patrick Calulo
 * 
 */
public class Chapter implements TreeNode {
	private String name;
	private int id;
	private List<VerseReference> verseRefs;

	private LinguistText linguistText;

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

	public Chapter() {
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

	public void setLinguistText(LinguistText parent) {
		this.linguistText = parent;
	}
	
	public LinguistText getLinguistText() {
		return this.linguistText;
	}

	public void printContents() {
		System.out.println("Chapter: " + getName() + " | ID: " + getId());
		System.out.println("Verse References: ");

		for (VerseReference verseRef : verseRefs) {
			verseRef.printContents();
		}
	}

	@Override
	public String toString() {
		return "Chapter " + getId();
	}
	
	// TreeNode interface methods

	@Override
	public Enumeration children() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getAllowsChildren() {
		// Chapters have VerseReferences inside them
		return true;
	}

	@Override
	public TreeNode getChildAt(int index) {
		return getVerseReferences().get(index);
	}

	@Override
	public int getChildCount() {
		return getVerseReferences().size();
	}

	@Override
	public int getIndex(TreeNode node) {
		return getVerseReferences().indexOf(node);
	}

	@Override
	public TreeNode getParent() {
		return getLinguistText();
	}

	@Override
	public boolean isLeaf() {
		// Chapters can have VerseReferences
		return false;
	}
}
