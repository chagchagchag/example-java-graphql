package io.chagchagchag.graphql.v1_graphql_simple.books.repository;

import io.chagchagchag.graphql.v1_graphql_simple.books.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
  List<Book> findByAuthorsName(String name);
  List<Book> findByTitleContaining(String keyword);
}
