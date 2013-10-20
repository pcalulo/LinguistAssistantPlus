package textgen.la.models.directory;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.tree.TreeNode;

public class LinguistText implements TreeNode {
	private String name;
	private List<Chapter> chapters;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

	public List<Chapter> getChapters() {
		return chapters;
	}

	public void addChapter(Chapter chapter) {
		this.chapters.add(chapter);
	}

	public void removeChapter(Chapter chapter) {
		this.chapters.remove(chapter);
	}

	public void printContents() {
		System.out.println("LinguistText: " + getName());
		System.out.println("Chapters:");
		for (Chapter chapter : chapters) {
			chapter.printContents();
		}
	}

	@Override
	public String toString() {
		return getName();
	}
	
	// TreeNode methods

	@Override
	public Enumeration children() {
		return new Vector<>(chapters).elements();
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return getChapters().get(childIndex);
	}

	@Override
	public int getChildCount() {
		return getChapters().size();
	}

	@Override
	public int getIndex(TreeNode node) {
		return getChapters().indexOf(node);
	}

	@Override
	public TreeNode getParent() {
		// LinguistText is the root node
		return null;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}
}
