package textgen.la.models.directory;

import java.util.List;

public class LinguistText {
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
}
