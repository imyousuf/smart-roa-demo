/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.demo.roa.services.impl;

import com.smartitengineering.dao.impl.hibernate.AbstractCommonDaoImpl;
import com.smartitengineering.demo.roa.domains.Author;
import com.smartitengineering.demo.roa.domains.Book;
import com.smartitengineering.demo.roa.services.BookService;
import java.util.Collection;

/**
 *
 * @author imyousuf
 */
public class BookServiceImpl extends AbstractCommonDaoImpl<Book> implements BookService {

  public BookServiceImpl() {
    setEntityClass(Book.class);
  }

  @Override
  public void save(Book book) {
    createEntity(book);
  }

  @Override
  public void update(Book book) {
    update(new Book[]{book});
  }

  @Override
  public void delete(Book book) {
    super.delete(book);
  }

  @Override
  public Book getByIsbn(String isbn) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public Collection<Book> getBooksForAuthor(Author author) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
