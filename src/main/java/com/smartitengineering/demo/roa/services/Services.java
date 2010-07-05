/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.demo.roa.services;

import com.smartitengineering.util.bean.BeanFactoryRegistrar;
import com.smartitengineering.util.bean.annotations.Aggregator;
import com.smartitengineering.util.bean.annotations.InjectableField;

/**
 *
 * @author imyousuf
 */
@Aggregator(contextName = "com.smartitengineering.demo.roa.services")
public class Services {

  @InjectableField
  private BookService bookService;
  @InjectableField
  private AuthorService authorService;

  private Services() {
  }

  public BookService getBookService() {
    return bookService;
  }

  public AuthorService getAuthorService() {
    return authorService;
  }
  private static Services services;

  public static Services getInstance() {
    if (services == null) {
      services = new Services();
      BeanFactoryRegistrar.aggregate(services);
    }
    return services;
  }
}
