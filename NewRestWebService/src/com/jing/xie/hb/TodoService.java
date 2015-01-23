package com.jing.xie.hb;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.jing.xie.jpa.Todos;

@Path("/abc")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Service("todoService")
public class TodoService {
  @Autowired
  private TodoDao todoDaoImpl;
  @GET
  public Todos getTodos() {
    Todos todos = new Todos(todoDaoImpl.findById());
    return todos;
  }
}
