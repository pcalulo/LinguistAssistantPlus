package textgen.la.models.directory;

import java.util.Enumeration;

import javax.swing.tree.TreeNode;

import textgen.la.models.Sentence;

public class VerseReference implements TreeNode {
	private String fileName;
	private int id;

	private Chapter chapter;

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

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public void printContents() {
		System.out.println("VerseReference: " + getFileName() + " | ID: "
				+ getId());
	}

	@Override
	public String toString() {
		return "Verse " + getId();
	}
	
	public Sentence getVerse() {
		return null;
	}

	// TreeNode interface methods

	@Override
	public Enumeration children() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getAllowsChildren() {
		// VerseReferences are leaf nodes
		return false;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getChildCount() {
		return 0;
	}

	@Override
	public int getIndex(TreeNode node) {
		return 0;
	}

	@Override
	public TreeNode getParent() {
		return getChapter();
	}

	@Override
	public boolean isLeaf() {
		// VerseReferences are leaf nodes
		return true;
	}
}
