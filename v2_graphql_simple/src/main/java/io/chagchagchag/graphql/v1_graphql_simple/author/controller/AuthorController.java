package io.chagchagchag.graphql.v1_graphql_simple.author.controller;

import io.chagchagchag.graphql.v1_graphql_simple.author.application.AuthorApplicationService;
import io.chagchagchag.graphql.v1_graphql_simple.author.model.AuthorModel;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AuthorController {
  private final AuthorApplicationService authorApplicationService;

  public AuthorController(AuthorApplicationService authorApplicationService){
    this.authorApplicationService = authorApplicationService;
  }

  @QueryMapping
  public List<AuthorModel> getAllAuthors(){
    return authorApplicationService.findAll();
  }

  @MutationMapping
  public AuthorModel addAuthor(@Argument String authorName){
    return authorApplicationService.saveAuthor(authorName);
  }

  @QueryMapping
  public AuthorModel getAuthorById(@Argument Long id){
    return authorApplicationService.findById(id);
  }
}
