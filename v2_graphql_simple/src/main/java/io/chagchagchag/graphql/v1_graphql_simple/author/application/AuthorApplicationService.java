package io.chagchagchag.graphql.v1_graphql_simple.author.application;

import io.chagchagchag.graphql.v1_graphql_simple.author.entity.Author;
import io.chagchagchag.graphql.v1_graphql_simple.author.mapper.AuthorMapper;
import io.chagchagchag.graphql.v1_graphql_simple.author.model.AuthorModel;
import io.chagchagchag.graphql.v1_graphql_simple.author.service.AuthorService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class AuthorApplicationService {
  private final AuthorService authorService;
  private final AuthorMapper authorMapper;

  @Transactional(readOnly = true)
  public List<AuthorModel> findAll(){
    return authorService.findAll()
        .stream()
        .map(author -> authorMapper.fromEntity(author))
        .collect(Collectors.toList());
  }

  @Transactional
  public AuthorModel saveAuthor(String authorName){
    Author author = Author.ofAll(null, authorName, null);
    Author saved = authorService.saveAuthor(author);
    return authorMapper.fromEntity(saved);
  }

  @Transactional(readOnly = true)
  public AuthorModel findById(Long id){
    return authorService.findById(id)
        .map(author -> authorMapper.fromEntity(author))
        .orElseThrow();
  }
}
