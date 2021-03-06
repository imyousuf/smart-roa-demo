/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.demo.roa.domains;

import com.smartitengineering.domain.AbstractPersistentDTO;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author imyousuf
 */
public class Book extends AbstractPersistentDTO<Book> {

  public static final String ISBN = "isbn";
  public static final String NAME = "name";
  public static final String AUTHORS = "authors";
  private String isbn;
  private String name;
  private Set<Author> authors;
  //The following will not be mapped in HBM its only for de/serialization
  private List<Integer> authorIds;
  private Date lastModifiedDate;

  @JsonIgnore
  public Date getLastModifiedDate() {
    return lastModifiedDate;
  }

  @JsonIgnore
  public void setLastModifiedDate(Date lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  @JsonIgnore
  public List<Integer> getAuthorIds() {
    return authorIds;
  }

  public void setAuthorIds(List<Integer> authorIds) {
    this.authorIds = authorIds;
  }

  @JsonIgnore
  public Set<Author> getAuthors() {
    return authors;
  }

  @JsonIgnore
  public void setAuthors(Set<Author> authors) {
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
  @JsonIgnore
  public boolean isValid() {
    Collection<Author> authorCollection = getAuthors();
    return StringUtils.isNotBlank(isbn) && StringUtils.isNotBlank(name) && authorCollection != null &&
        !authorCollection.isEmpty();
  }
}
