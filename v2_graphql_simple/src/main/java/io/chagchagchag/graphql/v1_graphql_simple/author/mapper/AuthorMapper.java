package io.chagchagchag.graphql.v1_graphql_simple.author.mapper;

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
public class AuthorMapper {
  public AuthorModel fromEntity(Author author){
    return new AuthorModel(
        author.getId(), author.getName(), fromBookSetEntity(author.getBooks())
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
    final BookMapper bookMapper = new BookMapper();
    return books.stream()
        .map(book -> bookMapper.fromEntity(book))
        .collect(Collectors.toSet());
  }

  public Set<ReviewModel> fromReviewSetEntity(Set<Review> reviews){
    final BookMapper bookMapper = new BookMapper();
    return reviews.stream()
        .map(review -> new ReviewModel(
              review.getId(),
              bookMapper.fromEntity(review.getBook()),
              review.getContent(),
              review.getRating(),
              review.getCreatedDate()
            )
        )
        .collect(Collectors.toSet());
  }

//  public Set<Author> fromAuthorModelSet(Set<AuthorModel> authorModelSet){
//    authorModelSet.stream()
//        .map(authorModel -> {
//          return Author.ofAll(authorModel.id(), authorModel.name(), authorModel.books())
//        })
//  }
//
//  public Set<Book> fromBookModelSet(Set<BookModel> bookModelSet){
//    return bookModelSet.stream()
//        .map(bookModel -> {
//          return new Book(
//              Book.ofAll(bookModel.id(), bookModel.title(), bookModel.publishedDate(),
//                  fromAuthorModelSet(bookModel.authors()),
//                  )
//          )
//        })
//  }

}
