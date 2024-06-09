package io.chagchagchag.graphql.v1_graphql_simple.author.controller;

import io.chagchagchag.graphql.v1_graphql_simple.author.entity.Author;
import io.chagchagchag.graphql.v1_graphql_simple.author.service.AuthorService;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AuthorController {
  private final AuthorService authorService;

  public AuthorController(AuthorService authorService){
    this.authorService = authorService;
  }

  @QueryMapping
  public List<Author> getAllAuthors(){
    return authorService.findAll();
  }

  @MutationMapping
  public Author addAuthor(@Argument String authorName){
    Author author = new Author();
    author.setName(authorName);
    return authorService.saveAuthor(author);
  }

  @QueryMapping
  public Author getAuthorById(@Argument Long id){
    return authorService.findById(id).orElseThrow();
  }
}
