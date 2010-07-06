/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.demo.roa;

import com.smartitengineering.demo.roa.domains.Author;
import com.smartitengineering.demo.roa.services.Services;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author imyousuf
 */
@Path("/authors/id/{authorId}")
public class AuthorResource extends AbstractResource {

  static final UriBuilder AUTHOR_URI_BUILDER = UriBuilder.fromResource(AuthorResource.class);
  static final UriBuilder AUTHOR_CONTENT_URI_BUILDER;

  static {
    AUTHOR_CONTENT_URI_BUILDER = AUTHOR_URI_BUILDER.clone();
    try {
      AUTHOR_CONTENT_URI_BUILDER.path(AuthorResource.class.getMethod("getAuthor"));
    }
    catch (Exception ex) {
      throw new InstantiationError();
    }
  }
  private Author author;

  public AuthorResource(@PathParam("authorId") Integer authorId) {
    author = Services.getInstance().getAuthorService().getById(authorId);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/content")
  public Response getAuthor() {
    return Response.ok(author).build();
  }
}
