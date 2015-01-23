package com.jing.xie;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/todo")
public class TodoResource {
  @GET
  @Produces({ MediaType.APPLICATION_JSON })
  public Todo getXML() {
    Todo todo = new Todo();
    todo.setId("1");
    todo.setSummary("This is my first todo");
    todo.setDescription("This is my first todo");
    return todo;
  }

  // This can be used to test the integration with the browser
  @GET
  @Produces({ MediaType.TEXT_HTML})
  public Todo getHTML() {
    Todo todo = new Todo();
    todo.setId("1");
    todo.setSummary("This is my first todo");
    todo.setDescription("This is my first todo");
    return todo;
  }
}
