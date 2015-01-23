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

}
