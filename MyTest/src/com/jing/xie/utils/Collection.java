package com.jing.xie.utils;

public interface Collection<E> extends Iterable<E> {
 int size();
 boolean isEmpty();
 boolean contains(Object e);
 Object[] toArray();
 <T> T[] toArray(T[] a);
 boolean add(E e);
 boolean remove(E e);
 boolean containsAll(Collection<?> c);
 boolean addAll(Collection<? extends E> c);
 boolean removeAll(Collection<?> c);
 boolean retainAll(Collection<?> c);
 void clear();
 boolean equals(Object e);
 int hashCode();
}
