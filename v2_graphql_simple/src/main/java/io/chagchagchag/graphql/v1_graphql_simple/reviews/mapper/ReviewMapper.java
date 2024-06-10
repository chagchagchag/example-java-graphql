package io.chagchagchag.graphql.v1_graphql_simple.reviews.mapper;

import io.chagchagchag.graphql.v1_graphql_simple.author.entity.Author;
import io.chagchagchag.graphql.v1_graphql_simple.author.model.AuthorModel;
import io.chagchagchag.graphql.v1_graphql_simple.books.entity.Book;
import io.chagchagchag.graphql.v1_graphql_simple.books.mapper.BookMapper;
import io.chagchagchag.graphql.v1_graphql_simple.books.model.BookModel;
import io.chagchagchag.graphql.v1_graphql_simple.reviews.entity.Review;
import io.chagchagchag.graphql.v1_graphql_simple.reviews.model.ReviewModel;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
  public ReviewModel fromEntity(Review review){
    return new ReviewModel(
        review.getId(), fromBook(review.getBook()), review.getContent(),
        review.getRating(), review.getCreatedDate()
    );
  }

  public BookModel fromBook(Book book){
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
        .map(book -> {
          BookMapper bookMapper = new BookMapper();
          return bookMapper.fromEntity(book);
        })
        .collect(Collectors.toSet());
  }

  public Set<ReviewModel> fromReviewSetEntity(Set<Review> reviews){
    return reviews.stream()
        .map(review -> {
          BookMapper bookMapper = new BookMapper();
          BookModel bookModel = bookMapper.fromEntity(review.getBook());
          return new ReviewModel(
              review.getId(), bookModel, review.getContent(),
              review.getRating(), review.getCreatedDate()
          );
        })
        .collect(Collectors.toSet());
  }
}
