package io.chagchagchag.graphql.v1_graphql_simple.reviews.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.chagchagchag.graphql.v1_graphql_simple.books.entity.Book;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "book_id", nullable = false)
  @JsonBackReference
  private Book book;

  @Column(nullable = false)
  private String content;

  private float rating;

  @Column(name = "created_date")
  @Temporal(TemporalType.TIMESTAMP)
  private OffsetDateTime createdDate;
}
