package com.jing.xie.hb;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jing.xie.jpa.Todo;

@Repository
@Transactional
public class TodoDaoImpl implements TodoDao {

  @Autowired
  private SessionFactory sessionFactory;

  @SuppressWarnings("unchecked")
  @Override
  public List<Todo> findById() {
    List<Todo> todos = new ArrayList<Todo>();
    todos = sessionFactory.getCurrentSession().createQuery("from Todo").list();
    return todos;
  }

  @Override
  public Todo findById(Long id) {
    List<Todo> todos = new ArrayList<Todo>();
    todos = sessionFactory.getCurrentSession().createQuery("from Todo where id =?").setParameter(0, id).list();
    if (todos.size() >0) {
      return todos.get(0);
    }
    return null;
  }

  @Override
  public Todo add(String summary, String description) {
    Todo todo = new Todo();
    todo.setId(new Long(5));
    todo.setSummary(summary);
    todo.setDescription(description);
    sessionFactory.getCurrentSession().saveOrUpdate(todo);
    return todo;
  }

}
