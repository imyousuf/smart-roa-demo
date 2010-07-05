/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.demo.roa.services;

/**
 * Thrown only if an author is not found!
 * @author imyousuf
 */
public class AuthorNotFoundException extends Exception {

  public AuthorNotFoundException(Throwable cause) {
    super(cause);
  }

  public AuthorNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public AuthorNotFoundException(String message) {
    super(message);
  }

  public AuthorNotFoundException() {
  }
}
