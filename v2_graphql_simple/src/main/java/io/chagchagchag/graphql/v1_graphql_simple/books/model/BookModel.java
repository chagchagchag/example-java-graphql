package io.chagchagchag.graphql.v1_graphql_simple.books.model;

import io.chagchagchag.graphql.v1_graphql_simple.author.model.AuthorModel;
import io.chagchagchag.graphql.v1_graphql_simple.reviews.model.ReviewModel;
import java.time.LocalDate;
import java.util.Set;

public record BookModel (
    Long id,
    String title,
    String publisher,
    LocalDate publishedDate,
    Set<AuthorModel> authors,
    Set<ReviewModel> reviews
){

}
