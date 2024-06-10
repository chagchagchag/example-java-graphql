package io.chagchagchag.graphql.v1_graphql_simple.author.model;

import io.chagchagchag.graphql.v1_graphql_simple.books.model.BookModel;
import java.util.Set;

public record AuthorModel (
    Long id,
    String name,
    Set<BookModel> books
){
}
