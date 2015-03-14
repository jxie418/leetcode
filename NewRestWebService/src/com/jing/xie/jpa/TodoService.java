package com.jing.xie.jpa;

import java.net.URI;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/def")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Stateless
public class TodoService {

  @PersistenceUnit(unitName="todos")
  private EntityManagerFactory factory;
  EntityManager entityManager;
  @PostConstruct
  public void setParams() {
    entityManager = factory.createEntityManager();
  }
  @Context
  private UriInfo uriInfo;

  @POST
  public Response createTodo(Todo todo) {
    entityManager.getTransaction().begin();
    entityManager.persist(todo);
    entityManager.getTransaction().commit();
    entityManager.close();
    URI uri = uriInfo.getAbsolutePathBuilder().path(todo.getId().toString()).build();
    return Response.created(uri).build();
  }

  @DELETE
  @Path("{id}")
  public Response deleteBook(@PathParam("id") String id) {
    entityManager.getTransaction().begin();
    entityManager.remove(entityManager.find(Todo.class, id));
    entityManager.getTransaction().commit();
    entityManager.close();
    return Response.noContent().build();
  }

  @GET
  @Path("{id}")
  public Response getBook(@PathParam("id") String id) {
    Todo todo = entityManager.find(Todo.class, id);
    if (todo == null) {
      throw new NotFoundException();
    }
    return Response.ok(todo).build();
  }

  @GET
  public Todos getTodos() {
    Query q = entityManager.createQuery("select t from Todo t");
    List<Todo> todoList = q.getResultList();
    //TypedQuery<Todo> query = entityManager.createNamedQuery("Todo.findAll", Todo.class);
    Todos todos = new Todos(todoList);
    return todos;
    // return Response.ok(books).build();
  }
}
