package com.jing.xie;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("employees")
public class Employees {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getEmployeeNames() {
      return "Martin, John";
    }
}
