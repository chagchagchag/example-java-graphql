package io.chagchagchag.graphql.v1_graphql_simple.books.mapper;

import io.chagchagchag.graphql.v1_graphql_simple.author.entity.Author;
import io.chagchagchag.graphql.v1_graphql_simple.author.model.AuthorModel;
import io.chagchagchag.graphql.v1_graphql_simple.books.entity.Book;
import io.chagchagchag.graphql.v1_graphql_simple.books.model.BookModel;
import io.chagchagchag.graphql.v1_graphql_simple.reviews.entity.Review;
import io.chagchagchag.graphql.v1_graphql_simple.reviews.model.ReviewModel;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
  public BookModel fromEntity(Book book){
    return new BookModel(
        book.getId(), book.getTitle(), book.getPublisher(),
        book.getPublishedDate(),
        fromAuthorsSetEntity(book.getAuthors()),
        fromReviewSetEntity(book.getReviews())
    );
  }

  public Set<AuthorModel> fromAuthorsSetEntity(Set<Author> authors){
    return authors.stream()
        .map(author -> {
          return new AuthorModel(
              author.getId(),
              author.getName(),
              fromBookSetEntity(author.getBooks())
          );
        })
        .collect(Collectors.toSet());
  }

  public Set<BookModel> fromBookSetEntity(Set<Book> books){
    return books.stream()
        .map(book -> fromEntity(book))
        .collect(Collectors.toSet());
  }

  public Set<ReviewModel> fromReviewSetEntity(Set<Review> reviews){
    return reviews.stream()
        .map(review -> {
          return new ReviewModel(
              review.getId(), fromEntity(review.getBook()), review.getContent(),
              review.getRating(), review.getCreatedDate()
          );
        })
        .collect(Collectors.toSet());
  }
}
