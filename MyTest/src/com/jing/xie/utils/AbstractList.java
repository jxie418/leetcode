package com.jing.xie.utils;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

import com.jing.xie.utils.AbstractList.SubList;

public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {
  protected transient int modCount = 0;
  protected AbstractList() {
  }

  public boolean add(E e) {
    add(size(), e);
    return true;
  }

  abstract public E get(int index);

  public E set(int index, E element) {
    throw new UnsupportedOperationException();
  }

  public void add(int index, E element) {
    throw new UnsupportedOperationException();
  }

  public E remove(int index) {
    throw new UnsupportedOperationException();
  }

  public int indexOf(Object o) {
    ListIterator<E> e = listIterator();
    if (o == null) {
      while (e.hasNext())
        if (e.next() == null)
          return e.previousIndex();
    } else {
      while (e.hasNext())
        if (o.equals(e.next()))
          return e.previousIndex();
    }
    return -1;
  }

  public int lastIndexOf(Object o) {
    ListIterator<E> e = listIterator(size());
    if (o == null) {
      while (e.hasPrevious())
        if (e.previous() == null) {
          return e.nextIndex();
        }
    } else {
      while (e.hasPrevious())
        if (o.equals(e.previous()))
          return e.nextIndex();
    }
    return -1;
  }

  public void clear() {
    removeRange(0, size());
  }

  protected void removeRange(int fromIndex, int toIndex) {
    ListIterator<E> it = listIterator();
    for (int i = 0, n = toIndex - fromIndex; i < n; i++) {
      it.next();
      it.remove();
    }
  }

  public boolean addAll(int index, Collection<? extends E> c) {
    boolean modified = false;
    Iterator<? extends E> e = c.iterator();
    while (e.hasNext()) {
      add(index++, e.next());
      modified = true;
    }
    return modified;
  }

  public Iterator<E> iterator() {
    return new Itr();
  }

  public ListIterator<E> listIterator() {
    return listIterator(0);
  }

  public ListIterator<E> listIterator(final int index) {
    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException("Index:" + index);
    }
    return new ListItr(index);
  }

  private class Itr implements Iterator<E> {
    protected transient int modCount = 0;
    int cursor = 0;
    int lastRet = -1;
    int expectedModCount = modCount;

    public boolean hasNext() {
      return cursor != size();
    }

    public E next() {
      checkForComodification();
      try {
        E next = get(cursor);
        lastRet = cursor++;
        return next;
      } catch (IndexOutOfBoundsException e) {
        checkForComodification();
        throw new NoSuchElementException();
      }
    }

    public void remove() {
      if (lastRet == -1) {
        throw new IllegalStateException();
      }
      checkForComodification();
      try {
        AbstractList.this.remove(lastRet);
      } catch (IndexOutOfBoundsException e) {
        throw new ConcurrentModificationException();
      }
    }

    final void checkForComodification() {
      if (modCount != expectedModCount) {
        throw new ConcurrentModificationException();
      }
    }
  }

  private class ListItr extends Itr implements ListIterator<E> {
    ListItr(int index) {
      cursor = index;
    }

    public E previous() {
      checkForComodification();
      try {
        int i = cursor - 1;
        E previous = get(i);
        lastRet = cursor = i;
        return previous;
      } catch (IndexOutOfBoundsException e) {
        checkForComodification();
        throw new NoSuchElementException();
      }
    }

    public int nextIndex() {
      return cursor;
    }

    public int previousIndex() {
      return cursor - 1;
    }

    public void set(E e) {
      if (lastRet == -1) {
        throw new IllegalStateException();
      }
      checkForComodification();
      try {
        AbstractList.this.set(lastRet, e);
      } catch (IndexOutOfBoundsException ex) {
        throw new ConcurrentModificationException();
      }
    }

    public void add(E e) {
      checkForComodification();
      try {
        AbstractList.this.add(cursor++, e);
        lastRet = -1;
        expectedModCount = modCount;
      } catch (IndexOutOfBoundsException ex) {
        throw new ConcurrentModificationException();
      }
    }

    public List<E> subList(int fromIndex, int toIndex) {
      /*return (this instanceof RandomAccess ? new RandomAccessSubList<E>(this,fromIndex, toIndex)
          : new SubList<E>(this, fromIndex, toIndex));*/
      return null;
    }

    public boolean equals(Object o) {
      if (o == this)
        return true;
      if (!(o instanceof List))
        return false;
      ListIterator<E> e1 = listIterator();
      ListIterator<E> e2 = ((List<E>) o).listIterator();
      while (e1.hasNext() && e2.hasNext()) {
        E o1 = e1.next();
        Object o2 = e2.next();
        if (!(o1 == null ? o2 == null : o1.equals(o2)))
          return false;
      }
      return !(e1.hasNext() || e2.hasNext());
    }

    public int hashCode() {
      int hashCode = 1;
      Iterator<E> i = iterator();
      while (i.hasNext()) {
        E obj = i.next();
        hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
      }
      return hashCode;
    }

    protected void removeRange(int fromIndex, int toIndex) {
      ListIterator<E> it = listIterator(fromIndex);
      for (int i = 0, n = toIndex - fromIndex; i < n; i++) {
        it.next();
        it.remove();
      }
    }

    @Override
    public boolean hasPrevious() {
      return cursor != 0;
    }
  }

  class SubList<E> extends AbstractList<E> {
    private AbstractList<E> l;
    private int offset;
    private int size;
    private int expectedModCount;

    SubList(AbstractList<E> list, int fromIndex, int toIndex) {
      if (fromIndex < 0)
        throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
      if (toIndex > list.size())
        throw new IndexOutOfBoundsException("toIndex = " + toIndex);
      if (fromIndex > toIndex)
        throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
      l = list;
      offset = fromIndex;
      size = toIndex - fromIndex;
      expectedModCount = l.modCount;
    }

    @Override
    public boolean remove(Collection<?> c) {
      // TODO Auto-generated method stub
      return false;
    }

    @Override
    public int lasIndexOf(Object o) {
      // TODO Auto-generated method stub
      return 0;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
      // TODO Auto-generated method stub
      return null;
    }

   

    @Override
    public E get(int index) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public int size() {
      // TODO Auto-generated method stub
      return 0;
    }
  }
}

