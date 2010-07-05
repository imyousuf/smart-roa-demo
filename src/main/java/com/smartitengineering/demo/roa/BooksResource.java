/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.demo.roa;

import com.smartitengineering.demo.roa.domains.Book;
import com.smartitengineering.demo.roa.services.AuthorNotFoundException;
import com.smartitengineering.demo.roa.services.Services;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import org.apache.abdera.Abdera;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;

/**
 *
 * @author imyousuf
 */
@Path("/books")
public class BooksResource {

  static final UriBuilder BOOK_URI_BUILDER;
  static final UriBuilder BOOKS_AFTER_ISBN_BUILDER;
  static final UriBuilder BOOKS_BEFORE_ISBN_BUILDER;

  static {
    BOOK_URI_BUILDER = UriBuilder.fromResource(BookResource.class);
    BOOKS_BEFORE_ISBN_BUILDER = UriBuilder.fromResource(BooksResource.class);
    try {
      BOOKS_BEFORE_ISBN_BUILDER.path(BooksResource.class.getMethod("getBefore", String.class));
    }
    catch (Exception ex) {
      throw new InstantiationError();
    }
    BOOKS_AFTER_ISBN_BUILDER = UriBuilder.fromResource(BooksResource.class);
    try {
      BOOKS_AFTER_ISBN_BUILDER.path(BooksResource.class.getMethod("getAfter", String.class));
    }
    catch (Exception ex) {
      throw new InstantiationError();
    }
  }
  private
  @QueryParam("name")
  String nameLike;
  private
  @QueryParam("author_nick")
  String authorNickName;
  private
  @QueryParam("count")
  Integer count;

  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  @Path("/before/{beforeIsbn}")
  public Response getBefore(@PathParam("beforeIsbn") String beforeIsbn) {
    return get(beforeIsbn, true);
  }

  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  @Path("/after/{afterIsbn}")
  public Response getAfter(@PathParam("afterIsbn") String afterIsbn) {
    return get(afterIsbn, false);
  }

  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  public Response get() {
    return get(null, true);
  }

  public Response get(String isbn, boolean isBefore) {
    if (count == null) {
      count = 10;
    }
    ResponseBuilder responseBuilder = Response.ok();
    Feed atomFeed = Abdera.getNewFactory().newFeed();
    Link booksLink = Abdera.getNewFactory().newLink();
    booksLink.setHref(UriBuilder.fromResource(RootResource.class).build().toString());
    booksLink.setRel("root");
    atomFeed.addLink(booksLink);
    Collection<Book> books = Services.getInstance().getBookService().getBooks(authorNickName, nameLike, isbn, isBefore,
                                                                              count);
    if (books != null && !books.isEmpty()) {
      List<Book> bookList = new ArrayList<Book>(books);
      Link nextLink = Abdera.getNewFactory().newLink();
      nextLink.setRel("next");
      Book lastBook = bookList.get(books.size() - 1);
      nextLink.setHref(BOOKS_BEFORE_ISBN_BUILDER.clone().build(lastBook.getIsbn()).toString());
      atomFeed.addLink(nextLink);
      Link prevLink = Abdera.getNewFactory().newLink();
      prevLink.setRel("prev");
      Book firstBook = bookList.get(books.size() - 1);
      prevLink.setHref(BOOKS_AFTER_ISBN_BUILDER.clone().build(firstBook.getIsbn()).toString());
      atomFeed.addLink(prevLink);
      for (Book book : books) {
        //ToDo Create entries
      }
    }
    responseBuilder.entity(atomFeed);
    return responseBuilder.build();

  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response post(Book book) {
    ResponseBuilder responseBuilder;
    try {
      Services.getInstance().getAuthorService().populateAuthor(book);
      Services.getInstance().getBookService().save(book);
      responseBuilder = Response.status(Status.CREATED);
    }
    catch (AuthorNotFoundException ex) {
      responseBuilder = Response.status(Status.BAD_REQUEST);
    }
    catch (Exception ex) {
      responseBuilder = Response.status(Status.INTERNAL_SERVER_ERROR);
    }
    return responseBuilder.build();
  }
}
