package io.chagchagchag.graphql.v1_graphql_simple.books.application;

import io.chagchagchag.graphql.v1_graphql_simple.author.entity.Author;
import io.chagchagchag.graphql.v1_graphql_simple.author.service.AuthorService;
import io.chagchagchag.graphql.v1_graphql_simple.books.entity.Book;
import io.chagchagchag.graphql.v1_graphql_simple.books.mapper.BookMapper;
import io.chagchagchag.graphql.v1_graphql_simple.books.model.BookModel;
import io.chagchagchag.graphql.v1_graphql_simple.books.service.BookService;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class BookApplicationService {
  private final AuthorService authorService;
  private final BookService bookService;
  private final BookMapper bookMapper;

  @Transactional
  public BookModel addBook(
      String title, String publisher,
      LocalDate publishedDate,
      List<Long> authorIds
  ){
    Set<Author> authors = new HashSet<>(authorService.findAllById(authorIds));
    Book book = bookService.saveBook(
        title,
        publisher,
        publishedDate,
        authors
    );
    return bookMapper.fromEntity(book);
  }

  @Transactional(readOnly = true)
  public BookModel findById(Long id) {
    return bookService.findById(id)
        .map(book -> bookMapper.fromEntity(book))
        .orElseThrow();
  }

  // 마음에 안드는 부분이지만, 강의 예제이기에 추가
  @Transactional(readOnly = true)
  public List<BookModel> findAll() {
    return bookService.findAll().stream()
        .map(book -> bookMapper.fromEntity(book))
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<BookModel> findByAuthorName(String authorName) {
    return bookService.findByAuthorName(authorName).stream()
        .map(book -> bookMapper.fromEntity(book))
        .collect(Collectors.toList());
  }

  @Transactional
  public Map<String, Boolean> deleteBook(Long id){
    bookService.deleteById(id);

    Map<String, Boolean> resultMap = new HashMap<>();
    resultMap.put("success", true);

    return resultMap;
  }
}
