package io.chagchagchag.graphql.v1_graphql_simple.version.model;

import java.util.Date;

public record VersionModel (String name, Date releaseDate){
  public static VersionModel getVersionModel(){
    return new VersionModel("1.0.0", new Date());
  }
}
