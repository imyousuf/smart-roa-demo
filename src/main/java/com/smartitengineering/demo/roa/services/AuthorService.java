/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.demo.roa.services;

import com.smartitengineering.demo.roa.domains.Author;
import com.smartitengineering.demo.roa.domains.Book;

/**
 *
 * @author imyousuf
 */
public interface AuthorService {

  public void save(Author author);

  public void update(Author author);

  public void delete(Author author);

  public Author getById(Integer authorId);

  public void populateAuthor(Book book) throws AuthorNotFoundException;
}
