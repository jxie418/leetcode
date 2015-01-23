package com.jing.xie.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(Todo.class)
public class Todos extends ArrayList<Todo> {

  private static final long serialVersionUID = 1L;

  public Todos() {
    super();
  }
  public Todos(Collection<? extends Todo> c) {
    super(c);
  }
  @XmlElement
  public List<Todo> getTodos(){
    return this;
  }
  public void setTodos(List<Todo> todos) {
    this.addAll(todos);
  }
}
