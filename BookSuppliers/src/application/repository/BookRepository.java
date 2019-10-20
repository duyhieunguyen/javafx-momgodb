package application.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import application.model.Book;

public interface BookRepository extends MongoRepository<Book, Long> {

	Book findFirstByBook(String book);

	@Query("{book:'?0'}")
	Book findCustomByBook(String book);

	@Query("{book: { $regex: ?0 } })")
	List<Book> findCustomByRegExBook(String book);
}
