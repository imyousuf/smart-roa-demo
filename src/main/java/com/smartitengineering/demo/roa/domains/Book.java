/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.demo.roa.domains;

import com.smartitengineering.domain.AbstractPersistentDTO;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author imyousuf
 */
public class Book extends AbstractPersistentDTO<Book> {

  public static final String ISBN = "isbn";
  public static final String AUTHORS = "authors";

  private String isbn;
  private String name;
  @JsonIgnore
  private List<Author> authors;

  @JsonIgnore
  public List<Author> getAuthors() {
    return authors;
  }

  @JsonIgnore
  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean isValid() {
    return StringUtils.isNotBlank(isbn) && StringUtils.isNotBlank(name);
  }
}
