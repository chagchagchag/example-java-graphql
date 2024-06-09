package io.chagchagchag.graphql.v1_graphql_simple.author.service;

import io.chagchagchag.graphql.v1_graphql_simple.author.entity.Author;
import io.chagchagchag.graphql.v1_graphql_simple.author.repository.AuthorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AuthorService {
  private final AuthorRepository authorRepository;
  public AuthorService(AuthorRepository authorRepository){
    this.authorRepository = authorRepository;
  }

  @Transactional(readOnly = true)
  public List<Author> findAll() {
    return authorRepository.findAll();
  }

  @Transactional
  public Author saveAuthor(Author author) {
    return authorRepository.save(author);
  }

  @Transactional(readOnly = true)
  public Optional<Author> findById(Long id) {
    return authorRepository.findById(id);
  }

  @Transactional(readOnly = true)
  public List<Author> findAllById(List<Long> ids){
    return authorRepository.findAllById(ids);
  }
}
