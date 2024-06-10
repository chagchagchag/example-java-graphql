package io.chagchagchag.graphql.v1_graphql_simple.books.controller;

import io.chagchagchag.graphql.v1_graphql_simple.books.application.BookApplicationService;
import io.chagchagchag.graphql.v1_graphql_simple.books.model.BookModel;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class BookController {
  private final BookApplicationService bookApplicationService;

  @MutationMapping
  public BookModel addBook(
      @Argument String title,
      @Argument String publisher,
      @Argument LocalDate publishedDate,
      @Argument List<Long> authorIds
  ){
    return bookApplicationService.addBook(title, publisher, publishedDate, authorIds);
  }

  @QueryMapping
  public BookModel getBookById(@Argument Long id) {
    return bookApplicationService.findById(id);
  }

  @QueryMapping
  public List<BookModel> getBooks() {
    return bookApplicationService.findAll();
  }

  @QueryMapping
  public List<BookModel> getBooksByAuthorName(@Argument String authorName) {
    return bookApplicationService.findByAuthorName(authorName);
  }

  @MutationMapping
  public Map<String, Boolean> deleteBook(@Argument Long id) {
    return bookApplicationService.deleteBook(id);
  }
}
