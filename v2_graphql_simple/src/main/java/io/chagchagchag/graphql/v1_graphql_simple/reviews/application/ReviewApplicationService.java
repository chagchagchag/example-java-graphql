package io.chagchagchag.graphql.v1_graphql_simple.reviews.application;

import io.chagchagchag.graphql.v1_graphql_simple.books.entity.Book;
import io.chagchagchag.graphql.v1_graphql_simple.books.service.BookService;
import io.chagchagchag.graphql.v1_graphql_simple.reviews.entity.Review;
import io.chagchagchag.graphql.v1_graphql_simple.reviews.mapper.ReviewMapper;
import io.chagchagchag.graphql.v1_graphql_simple.reviews.model.ReviewModel;
import io.chagchagchag.graphql.v1_graphql_simple.reviews.service.ReviewService;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class ReviewApplicationService {
  private final ReviewService reviewService;
  private final BookService bookService;
  private final ReviewMapper reviewMapper;

  @Transactional
  public ReviewModel saveReview(
      Long bookId,
      String content,
      Float rating
  ) {
    Book book = bookService.findById(bookId).orElseThrow();
    final Review newReview = Review.ofAll(null, book, content, rating, OffsetDateTime.now());
    final Review saved = reviewService.saveReview(newReview);
    return reviewMapper.fromEntity(saved);
  }

  public List<ReviewModel> findByBookId(Long id) {
    return reviewService.findByBookId(id)
        .stream()
        .map(review -> reviewMapper.fromEntity(review))
        .collect(Collectors.toList());
  }

  public Map<String, Boolean> deleteById(Long reviewId){
    reviewService.deleteById(reviewId);
    Map<String, Boolean> resultMap = new HashMap<>();
    resultMap.put("success", true);

    return resultMap;
  }
}
