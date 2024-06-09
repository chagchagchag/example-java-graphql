package io.chagchagchag.graphql.v1_graphql_simple.version.rest;

import io.chagchagchag.graphql.v1_graphql_simple.version.model.VersionModel;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class VersionController {
  @QueryMapping
  public VersionModel getVersionModel(){
    return VersionModel.getVersionModel();
  }
}


