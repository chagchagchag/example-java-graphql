package io.chagchagchag.graphql.v1_graphql_simple.books.service;

import io.chagchagchag.graphql.v1_graphql_simple.author.entity.Author;
import io.chagchagchag.graphql.v1_graphql_simple.books.entity.Book;
import io.chagchagchag.graphql.v1_graphql_simple.books.repository.BookRepository;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookService {
  private final BookRepository bookRepository;

  @Transactional
  public Book saveBook(Book book){
    return bookRepository.save(book);
  }

  @Transactional
  public Book saveBook(
      String title,
      String publisher,
      LocalDate publishedDate,
      Set<Author> authors
  ){
    Book newBook = Book.ofAll(
        null,
        title,
        publisher,
        publishedDate,
        authors,
        null
    );
    return bookRepository.save(newBook);
  }

  @Transactional(readOnly = true)
  public Optional<Book> findById(Long id){
    return bookRepository.findById(id);
  }

  @Transactional(readOnly = true)
  public List<Book> findAll(){
    return bookRepository.findAll();
  }

  @Transactional(readOnly = true)
  public List<Book> findByAuthorName(String authorName){
    return bookRepository.findByAuthorsName(authorName);
  }

  @Transactional
  public void deleteById(Long id){
    bookRepository.deleteById(id);
  }
}
