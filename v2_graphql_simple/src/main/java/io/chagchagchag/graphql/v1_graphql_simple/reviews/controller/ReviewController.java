package io.chagchagchag.graphql.v1_graphql_simple.reviews.controller;

import io.chagchagchag.graphql.v1_graphql_simple.reviews.application.ReviewApplicationService;
import io.chagchagchag.graphql.v1_graphql_simple.reviews.entity.Review;
import io.chagchagchag.graphql.v1_graphql_simple.reviews.model.ReviewModel;
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
  private final ReviewApplicationService reviewApplicationService;

  @MutationMapping
  public ReviewModel addReview(
      @Argument Long bookId,@Argument String content,@Argument Float rating
  ){
    return reviewApplicationService.saveReview(bookId, content, rating);
  }

  @QueryMapping
  public List<ReviewModel> getReviewsByBookId(@Argument Long id) {
    return reviewApplicationService.findByBookId(id);
  }

  @MutationMapping
  public Map<String, Boolean> deleteReviewById(@Argument Long reviewId) {
    return reviewApplicationService.deleteById(reviewId);
  }
}
