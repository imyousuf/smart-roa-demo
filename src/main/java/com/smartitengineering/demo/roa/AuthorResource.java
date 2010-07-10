/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.demo.roa;

import com.smartitengineering.demo.roa.domains.Author;
import com.smartitengineering.demo.roa.services.Services;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriBuilderException;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;

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

  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  public Response get() {
    Feed authorFeed = getAuthorFeed();
    ResponseBuilder responseBuilder = Response.ok(authorFeed);
    return responseBuilder.build();
  }
  @PUT
  @Produces(MediaType.APPLICATION_ATOM_XML)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response update(Author newAuthor) {
    ResponseBuilder responseBuilder = Response.status(Status.SERVICE_UNAVAILABLE);
    try {
      Services.getInstance().getAuthorService().update(newAuthor);
      author = Services.getInstance().getAuthorService().getById(author.getId());
      responseBuilder = Response.ok(getAuthorFeed());
    }
    catch (Exception ex) {
      responseBuilder = Response.status(Status.INTERNAL_SERVER_ERROR);
    }
    return responseBuilder.build();
  }

  @POST
  @Produces(MediaType.APPLICATION_ATOM_XML)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response ammend(Author newAuthor) {
    return update(newAuthor);
  }

  @DELETE
  public Response delete() {
    Services.getInstance().getAuthorService().delete(author);
    ResponseBuilder responseBuilder = Response.ok();
    return responseBuilder.build();
  }

  private Feed getAuthorFeed() throws UriBuilderException, IllegalArgumentException {
    Feed bookFeed = getFeed(author.getName(), author.getLastModifiedDate());
    Link editLink = abderaFactory.newLink();
    editLink.setHref(uriInfo.getRequestUri().toString());
    editLink.setRel(Link.REL_EDIT);
    editLink.setMimeType(MediaType.APPLICATION_JSON);
    Link altLink = abderaFactory.newLink();
    altLink.setHref(AUTHOR_CONTENT_URI_BUILDER.clone().build(author.getId()).toString());
    altLink.setRel(Link.REL_ALTERNATE);
    altLink.setMimeType(MediaType.APPLICATION_JSON);
    bookFeed.addLink(altLink);
    return bookFeed;
  }
}
