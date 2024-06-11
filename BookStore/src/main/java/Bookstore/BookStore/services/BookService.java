package Bookstore.BookStore.services;

import Bookstore.BookStore.repository.IBookRepository;
import Bookstore.BookStore.entity.Book;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private IBookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBookById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new IllegalStateException("Product with ID " + id + " does not exist.");
        }
        bookRepository.deleteById(id);
    }

    public Book updateBook(@NotNull Book book) {
        Book existingBook = bookRepository.findById(book.getId())
                .orElseThrow(() -> new IllegalStateException("Product with ID " +
                        book.getId() + " does not exist."));
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPrice(book.getPrice());
        existingBook.setCategory(book.getCategory());
        return bookRepository.save(existingBook);
    }
}
