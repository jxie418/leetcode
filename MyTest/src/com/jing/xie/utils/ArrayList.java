package com.jing.xie.utils;

import java.util.Arrays;
import java.util.RandomAccess;

public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable {

  private static final long serialVersionUID = 8683452581122892189L;
  
  private transient Object[] elementData;
  
  private int size;
  
  public ArrayList(int initialCapacity) {
    super();
    if (initialCapacity <0)
      throw new IllegalArgumentException("Illegal Capacity :" + initialCapacity);
    this.elementData = new Object[initialCapacity];
  }
  public ArrayList() {
    this(10);
  }
  
  public void ensureCapacity(int minCapacity) {
    modCount++;
    int oldCapacity = elementData.length;
    if (minCapacity > oldCapacity) {
      Object oldData[] = elementData;
      int newCapacity =(oldCapacity * 3) /2 +1;
      if (newCapacity < minCapacity)
          newCapacity = minCapacity;
      elementData = Arrays.copyOf(elementData, newCapacity);
    }
  }
  public boolean isEmpty() {
    return size == 0;
  }
  
  public int indexOf(Object o) {
    if (o == null) {
      for(int i=0 ; i< size; i++)
        if(elementData[i] == null)
          return i;
    } else {
      for(int i = 0; i< size; i++)
         if(o.equals(elementData[i]))
           return i;
    }
    return -1;
  }
  
  public int lastIndexOf(Object o) {
    if (o == null) {
      for(int i = size -1; i >=0; i--)
        if (elementData[i] == null)
            return i;
    } else {
      for(int i = size -1; i>= 0; i--)
        if(o.equals(elementData[i]))
          return i;
    }
    return -1;
  }
  
  
  
  public Object clone() {
    try{
      ArrayList<E> v = (ArrayList<E>) super.clone();
      v.elementData = Arrays.copyOf(elementData, size);
      v.modCount = 0 ;
      return v;
    } catch(CloneNotSupportedException e) {
      throw new InternalError();
    }
  }
  
  public E remove(int index) {
    RangeCheck(index);
    modCount++;
    E oldValue = (E)elementData[index];
    int numMoved = size - index -1;
    if (numMoved >0)
      System.arraycopy(elementData, index+1, elementData, index, numMoved);
    elementData[--size] = null;
    return oldValue;
  }

  @Override
  public E get(int index) {
    RangeCheck(index);
    return (E) elementData[index];
  }

  
  public E set(int index, E element) {
    RangeCheck(index);
    E oldValue = (E) elementData[index];
    elementData[index] = element;
    return oldValue;
  }
  public boolean add(E e) {
    ensureCapacity(size +1);
    elementData[size++] = e;
    return true;
  }
  
  public void add(int index, E element) {
    if (index > size || index < 0)
      throw new IndexOutOfBoundsException("Index:" +index+", Size :" + size);
    ensureCapacity(size+1);
    System.arraycopy(elementData, index, elementData, index+1, size - index);
    elementData[index] = element;
    size++;
  }
  
  private void RangeCheck(int index) {
    if(index >= size)
      throw new IndexOutOfBoundsException("Index : " + index+", Size: " + size);
  }
  
 
  @Override
  public int size() {
    // TODO Auto-generated method stub
    return size;
  }
  @Override
  public boolean remove(Object e) {
    // TODO Auto-generated method stub
    return false;
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

}
