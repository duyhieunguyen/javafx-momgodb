package application.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.mongodb.client.model.Filters;

import application.configuration.ConnectMongoDB;
import application.model.Book;

public class BookService {

	public static List<Book> bookList = new ArrayList<Book>();

	ConnectMongoDB con = new ConnectMongoDB();
	Gson g = new Gson();

	public void insertBookSupplier(Book book) {

		Document doc = new Document();
		doc = Document.parse(g.toJson(book));
		con.collection.insertOne(doc);
	}

	public void updateBookSupplier(Book book, String id) {
		Document doc = new Document();
		doc = Document.parse(g.toJson(book));
		Bson filter = Filters.eq("supplierId", id);
		con.collection.replaceOne(filter, doc);

	}

	public void deleteBookSupplier(String supplierId) {
		Bson filter = Filters.eq("supplierId", supplierId);
		con.collection.deleteOne(filter);
	}

	public List<Book> getAllBookSuppliers() {
		List<Book> bookList = new ArrayList<Book>();
		for (Document doc : con.findIterable) {
			Book b = g.fromJson(doc.toJson().toString(), Book.class);
			bookList.add(b);
		}
		return bookList;
	}

}
