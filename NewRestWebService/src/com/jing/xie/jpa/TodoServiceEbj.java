package com.jing.xie.jpa;

import java.net.URI;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
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

/**
 * Session Bean implementation class TodoServiceEbj
 */

@Stateless
@LocalBean
public class TodoServiceEbj {

    /**
     * Default constructor. 
     */
    public TodoServiceEbj() {
    }

    public String sayHello(String name) {
      return "Hello " + name ;
    }
}
