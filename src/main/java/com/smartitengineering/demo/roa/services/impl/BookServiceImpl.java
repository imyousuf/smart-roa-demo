/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.demo.roa.services.impl;

import com.smartitengineering.dao.common.queryparam.Order;
import com.smartitengineering.dao.common.queryparam.QueryParameterFactory;
import com.smartitengineering.dao.impl.hibernate.AbstractCommonDaoImpl;
import com.smartitengineering.demo.roa.domains.Author;
import com.smartitengineering.demo.roa.domains.Book;
import com.smartitengineering.demo.roa.services.BookService;
import java.util.Collection;
import java.util.LinkedHashSet;

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
    return getSingle(QueryParameterFactory.getStringLikePropertyParam(Book.ISBN, isbn));
  }

  @Override
  public Collection<Book> getBooksForAuthor(Author author) {
    return new LinkedHashSet<Book>(getList(QueryParameterFactory.getEqualPropertyParam(Book.AUTHORS, author), QueryParameterFactory.
        getOrderByParam(Book.ISBN, Order.DESC)));
  }
}
