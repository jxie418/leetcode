package com.jing.xie.hb;

import java.util.List;

import com.jing.xie.jpa.Todo;

public interface TodoDao {
  List<Todo> findById();
}
