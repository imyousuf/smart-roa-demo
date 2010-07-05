/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.demo.roa.services;

import com.smartitengineering.demo.roa.domains.Author;

/**
 *
 * @author imyousuf
 */
public interface AuthorService {

  public void save(Author book);

  public void update(Author book);

  public void delete(Author book);

  public Author getById(Integer authorId);
}
