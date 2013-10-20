package textgen.la.models.directory;

import java.awt.print.Book;
import java.io.File;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.swing.tree.TreeNode;

import textgen.la.models.Parser;
import textgen.la.models.Sentence;

public class VerseReference implements TreeNode {
	private String fileName;
	private int id;

	private Chapter chapter;
	private Sentence sentence;
	private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

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
		// If we've already loaded the verse, simply return it
		if (sentence != null) {
			return sentence;
		}
		
		// Otherwise, figure out where that verse is and load it
		Chapter chapter = getChapter();
		LinguistText lingText = chapter.getLinguistText();
		File directory = lingText.getDirectory();
		
		String path = directory.getPath();
		String verseFilePath = path + File.separator + getFileName();
		File file;
		
		logger.info("Loading verse file " + verseFilePath);
		file = new File(verseFilePath);
		Parser parser = new Parser(file);
		
		sentence = parser.getSentence();
		
		return sentence;
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
