package io.chagchagchag.graphql.v1_graphql_simple.reviews.repository;

import io.chagchagchag.graphql.v1_graphql_simple.reviews.entity.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
  List<Review> findByBookIdOrderByCreatedDateDesc(Long bookId);
}
