/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.demo.roa;

import com.smartitengineering.demo.roa.domains.Book;
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

/**
 *
 * @author imyousuf
 */
@Path("/books/{isbn}")
public class BookResource {

  private Book book;

  public BookResource(@PathParam("isbn") String isbn) {
    book = Services.getInstance().getBookService().getByIsbn(isbn);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response get() {
    ResponseBuilder responseBuilder = Response.ok(book);
    return responseBuilder.build();
  }

  @PUT
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response update(Book newBook) {
    ResponseBuilder responseBuilder = Response.status(Status.SERVICE_UNAVAILABLE);
    return responseBuilder.build();
  }

  @DELETE
  public Response delete() {
    ResponseBuilder responseBuilder = Response.status(Status.SERVICE_UNAVAILABLE);
    return responseBuilder.build();
  }
}
