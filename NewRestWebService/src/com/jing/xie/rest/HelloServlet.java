package com.jing.xie.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jing.xie.jpa.Todo;
import com.jing.xie.jpa.TodoServiceEbj;

/**
 * Servlet implementation class HelloServlet
 */
public class HelloServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @PersistenceUnit 
  EntityManagerFactory factory;
  @EJB TodoServiceEbj bean;
  /**
   * @see HttpServlet#HttpServlet()
   */
  public HelloServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    EntityManager entityManager = factory.createEntityManager();
    //read the existing entries and write to console
    Query q = entityManager.createQuery("select t from Todo t");
    List<Todo> todoList = q.getResultList();
    for (Todo todo : todoList) {
      System.out.println(todo);
    }
    System.out.println("Size: " + todoList.size());

    // create new todo
    entityManager.getTransaction().begin();
    Todo todo = new Todo();
    todo.setSummary("This is a test");
    todo.setDescription("This is a test");
    entityManager.persist(todo);
    entityManager.getTransaction().commit();

    entityManager.close();
    PrintWriter out = response.getWriter();
    out.print("<html><body>");
    out.print("<h1>Request served at: " + request.getServletPath() +"</h1>");
    out.print("<h2>" +bean.sayHello("James") + "</h2>");
    out.print("</html></body>");
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
  }

}
