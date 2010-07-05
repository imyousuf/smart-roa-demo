/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.demo.roa;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  public Response get() {
    ResponseBuilder responseBuilder = Response.ok();
    Feed atomFeed = Abdera.getNewFactory().newFeed();
    Link booksLink = Abdera.getNewFactory().newLink();
    booksLink.setHref(UriBuilder.fromResource(RootResource.class).build().toString());
    booksLink.setRel("root");
    atomFeed.addLink(booksLink);
    responseBuilder.entity(atomFeed);
    return responseBuilder.build();
  }
  @POST
  public Response post() {
    ResponseBuilder responseBuilder = Response.status(Status.SERVICE_UNAVAILABLE);
    return responseBuilder.build();
  }
}
