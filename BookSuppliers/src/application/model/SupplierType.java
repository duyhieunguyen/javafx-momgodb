package application.model;

public class SupplierType {
	private String books;
	private String newspaper;
	private String journalsAndMagazines;

	public String getBooks() {
		return books;
	}

	public void setBooks(String books) {
		this.books = books;
	}

	public String getNewspaper() {
		return newspaper;
	}

	public void setNewspaper(String newspaper) {
		this.newspaper = newspaper;
	}

	public String getOurnalsAndMagazines() {
		return journalsAndMagazines;
	}

	public void setOurnalsAndMagazines(String ournalsAndMagazines) {
		this.journalsAndMagazines = ournalsAndMagazines;
	}

	@Override
	public String toString() {
		return "SupplierType [books=" + books + ", newspaper=" + newspaper + ", ournalsAndMagazines="
				+ journalsAndMagazines + "]";
	}

}
