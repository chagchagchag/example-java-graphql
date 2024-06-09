package io.chagchagchag.graphql.v1_graphql_simple.author.repository;

import io.chagchagchag.graphql.v1_graphql_simple.author.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
