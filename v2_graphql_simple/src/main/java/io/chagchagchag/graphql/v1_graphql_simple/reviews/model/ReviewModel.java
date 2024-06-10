package io.chagchagchag.graphql.v1_graphql_simple.reviews.model;

import io.chagchagchag.graphql.v1_graphql_simple.books.model.BookModel;
import java.time.OffsetDateTime;

public record ReviewModel(
    Long id,
    BookModel book,
    String content,
    Float rating,
    OffsetDateTime createdDate
) {
}
