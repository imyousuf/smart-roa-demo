/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.demo.roa.services.impl;

import com.smartitengineering.dao.impl.hibernate.AbstractCommonDaoImpl;
import com.smartitengineering.demo.roa.domains.Author;
import com.smartitengineering.demo.roa.domains.Book;
import com.smartitengineering.demo.roa.services.AuthorNotFoundException;
import com.smartitengineering.demo.roa.services.AuthorService;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author imyousuf
 */
public class AuthorServiceImpl extends AbstractCommonDaoImpl<Author> implements AuthorService {

  @Override
  public void save(Author author) {
    if(!author.isValid()) {
      throw new IllegalStateException("Author is not valid!");
    }
    author.setLastModifiedDate(new Date());
    super.save(author);
  }

  @Override
  public void update(Author author) {
    if(!author.isValid()) {
      throw new IllegalStateException("Author is not valid!");
    }
    author.setLastModifiedDate(new Date());
    super.update(author);
  }

  @Override
  public void delete(Author author) {
    if(!author.isValid()) {
      throw new IllegalStateException("Author is not valid!");
    }
    super.delete(author);
  }

  @Override
  public void populateAuthor(Book book) throws AuthorNotFoundException {
    List<Integer> authorIds = book.getAuthorIds();
    if (authorIds != null && !authorIds.isEmpty()) {
      Set<Author> authors = getByIds(authorIds);
      if (authors == null || authorIds.size() != authors.size()) {
        throw new AuthorNotFoundException();
      }
      book.setAuthors(new LinkedHashSet<Author>(authors));
    }
  }
}
