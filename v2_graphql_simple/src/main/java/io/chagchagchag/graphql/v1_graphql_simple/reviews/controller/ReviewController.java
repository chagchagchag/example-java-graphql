package io.chagchagchag.graphql.v1_graphql_simple.reviews.controller;

import io.chagchagchag.graphql.v1_graphql_simple.books.service.BookService;
import io.chagchagchag.graphql.v1_graphql_simple.reviews.entity.Review;
import io.chagchagchag.graphql.v1_graphql_simple.reviews.service.ReviewService;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ReviewController {
  private final ReviewService reviewService;
  private final BookService bookService;

  @MutationMapping
  public Review addReview(
      @Argument Long bookId,@Argument String content,@Argument Float rating
  ){
    Review newReview = new Review();
    newReview.setBook(bookService.findById(bookId).orElseThrow());
    newReview.setContent(content);
    newReview.setRating(rating);
    newReview.setCreatedDate(OffsetDateTime.now());

    return reviewService.saveReview(newReview);
  }

  @QueryMapping
  public List<Review> getReviewsByBookId(@Argument Long id) {
    return reviewService.findByBookId(id);
  }

  @MutationMapping
  public Map<String, Boolean> deleteReviewById(@Argument Long reviewId) {
    reviewService.deleteById(reviewId);
    Map<String, Boolean> resultMap = new HashMap<>();
    resultMap.put("success", true);

    return resultMap;
  }
}
