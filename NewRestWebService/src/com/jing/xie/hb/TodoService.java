package com.jing.xie.hb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jing.xie.jpa.Todo;
import com.jing.xie.jpa.Todos;

@RestController
@RequestMapping("/data")
public class TodoService {
  @Autowired
  private TodoDao todoDaoImpl;

  @RequestMapping("/todos")
  public Todos getTodos() {
    Todos todos = new Todos(todoDaoImpl.findById());
    return todos;
  }

  @RequestMapping("/onetodo")
  public Todo getTodo(@RequestParam(value = "id", required = false, defaultValue = "0") Long id) {
    return todoDaoImpl.findById(id);
  }

  @RequestMapping("/add")
  public Todo add(
      @RequestParam(value = "summary", required = true) String summary,
      @RequestParam(value = "description", required = true) String description) {
    return todoDaoImpl.add(summary, description);
  }
}
