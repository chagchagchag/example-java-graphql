package io.chagchagchag.graphql.v1_graphql_simple.reviews.service;

import io.chagchagchag.graphql.v1_graphql_simple.reviews.entity.Review;
import io.chagchagchag.graphql.v1_graphql_simple.reviews.repository.ReviewRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewService {
  private final ReviewRepository reviewRepository;

  public Review saveReview(Review review) {
    return reviewRepository.save(review);
  }


  public Optional<Review> findById(Long id) {
    return reviewRepository.findById(id);
  }

  public List<Review> findByBookId(Long bookId) {
    return reviewRepository.findByBookIdOrderByCreatedDateDesc(bookId);
  }

  public void deleteById(Long id) {
    reviewRepository.deleteById(id);
  }
}
