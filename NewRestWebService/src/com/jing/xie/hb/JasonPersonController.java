package com.jing.xie.hb;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestParam;

import com.jing.xie.bean.Person;

@Path("/json")
public class JasonPersonController {
  

  @GET
  @Path("/get")
  @Produces(MediaType.APPLICATION_JSON)
  public Person getPersonDetail(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {
    Person p = new Person();
    p.setId(1);
    p.setLocation("China");
    p.setName("HuBei");
    return p;
  }
  
  @POST
  @Path("/post")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response  getPersonJson(Person person) {
    String result = "Person saved : " + person;
    return Response.status(201).entity(result).build();
  }
}
