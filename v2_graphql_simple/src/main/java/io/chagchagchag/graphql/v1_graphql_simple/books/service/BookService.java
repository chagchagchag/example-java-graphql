package io.chagchagchag.graphql.v1_graphql_simple.books.service;

import io.chagchagchag.graphql.v1_graphql_simple.books.entity.Book;
import io.chagchagchag.graphql.v1_graphql_simple.books.repository.BookRepository;
import java.util.List;
import java.util.Optional;
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
