package textgen.la.models.directory;

import java.io.PrintStream;
import java.util.List;

public class LinguistText {
	private String name;
	private List<Book> books;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	public List<Book> getBooks() {
		return books;
	}
	
	public void addBook(Book book) {
		this.books.add(book);
	}
	
	public void removeBook(Book book) {
		this.books.remove(book);
	}
	
	public void printContents() {
		System.out.println("LinguistText: " + getName());
		System.out.println("Books:");
		for (Book book : books) {
			book.printContents();
		}
	}
}
