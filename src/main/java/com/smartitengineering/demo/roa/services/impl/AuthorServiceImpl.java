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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author imyousuf
 */
public class AuthorServiceImpl extends AbstractCommonDaoImpl<Author> implements AuthorService {

  @Override
  public void save(Author author) {
    super.save(author);
  }

  @Override
  public void update(Author author) {
    super.update(author);
  }

  @Override
  public void delete(Author author) {
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
      book.setAuthors(new ArrayList<Author>(authors));
    }
  }
}
