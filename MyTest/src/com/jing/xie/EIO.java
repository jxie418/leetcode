package com.jing.xie;

import java.util.Iterator;

public class EIO implements Iterator<Integer> {
  public Iterator<Integer> it;
  int value;

  public EIO(Iterator<Integer> it) {
    this.it = it;
  }

  @Override
  public boolean hasNext() {
    Iterator<Integer> tmp = it;
    while (tmp.hasNext() && tmp.next() % 2 != 0) {
    }
    if (tmp.hasNext()) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public Integer next() {
    while (it.hasNext() && (value = it.next()) % 2 != 0) {
    }
    if (it.hasNext()) {
      return value;
    } else {
      
      return 0;
    }

  }

  @Override
  public void remove() {

  }

}
