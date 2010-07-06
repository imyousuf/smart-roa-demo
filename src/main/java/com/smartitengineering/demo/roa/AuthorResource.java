/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.demo.roa;

import javax.ws.rs.Path;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author imyousuf
 */
@Path("/authors/{nickname}")
public class AuthorResource extends AbstractResource {
  static final UriBuilder AUTHOR_URI_BUILDER = UriBuilder.fromResource(AuthorResource.class);
}
