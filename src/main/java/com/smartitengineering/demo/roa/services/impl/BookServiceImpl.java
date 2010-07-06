/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.demo.roa.services.impl;

import com.smartitengineering.dao.common.queryparam.FetchMode;
import com.smartitengineering.dao.common.queryparam.MatchMode;
import com.smartitengineering.dao.common.queryparam.Order;
import com.smartitengineering.dao.common.queryparam.QueryParameter;
import com.smartitengineering.dao.common.queryparam.QueryParameterFactory;
import com.smartitengineering.dao.impl.hibernate.AbstractCommonDaoImpl;
import com.smartitengineering.demo.roa.domains.Author;
import com.smartitengineering.demo.roa.domains.Book;
import com.smartitengineering.demo.roa.services.BookService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import org.apache.commons.lang.StringUtils;

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
    if(!book.isValid()) {
      throw new IllegalStateException("Book is not valid!");
    }
    book.setLastModifiedDate(new Date());
    createEntity(book);
  }

  @Override
  public void update(Book book) {
    if(!book.isValid()) {
      throw new IllegalStateException("Book is not valid!");
    }
    book.setLastModifiedDate(new Date());
    update(new Book[]{book});
  }

  @Override
  public void delete(Book book) {
    if(!book.isValid()) {
      throw new IllegalStateException("Book is not valid!");
    }
    super.delete(book);
  }

  @Override
  public Book getByIsbn(String isbn) {
    return getSingle(QueryParameterFactory.getStringLikePropertyParam(Book.ISBN, isbn));
  }

  @Override
  public Collection<Book> getBooks(String authorNickNameLike, String bookNameLike, String isbn, boolean isSmallerThan,
                                   int count) {
    List<QueryParameter> params = new ArrayList<QueryParameter>();
    if (StringUtils.isNotBlank(authorNickNameLike)) {
      final QueryParameter authorParam = QueryParameterFactory.getNestedParametersParam(Book.AUTHORS, FetchMode.EAGER,
                                                                                        QueryParameterFactory.
          getStringLikePropertyParam(Author.UNIQUE_NICK_NAME, authorNickNameLike));
      params.add(authorParam);
    }
    if (StringUtils.isNotBlank(bookNameLike)) {
      params.add(QueryParameterFactory.getStringLikePropertyParam(Book.NAME, bookNameLike, MatchMode.ANYWHERE));
    }
    if (StringUtils.isNotBlank(isbn)) {
      if (isSmallerThan) {
        params.add(QueryParameterFactory.getLesserThanEqualToPropertyParam(Book.ISBN, isbn));
      }
      else {
        params.add(QueryParameterFactory.getGreaterThanEqualToPropertyParam(Book.ISBN, isbn));
      }
    }
    params.add(QueryParameterFactory.getMaxResultsParam(count));
    params.add(QueryParameterFactory.getOrderByParam(Book.ISBN, Order.DESC));
    return new LinkedHashSet<Book>(getList(params));
  }
}
