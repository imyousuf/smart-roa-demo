/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.demo.roa.services;

import com.smartitengineering.demo.roa.domains.Author;
import com.smartitengineering.demo.roa.domains.Book;
import java.util.Collection;

/**
 *
 * @author imyousuf
 */
public interface BookService {

  public void save(Book book);

  public void update(Book book);

  public void delete(Book book);

  public Book getByIsbn(String isbn);

  public Collection<Book> getBooksForAuthor(Author author);
}
