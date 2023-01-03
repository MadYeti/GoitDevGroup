package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MyArrayList <E>{

  private E[] array;

  public MyArrayList(E[] array) {
    this.array = array;
  }

  public int size() {
    for (int i = 0; i < this.array.length; i++) {
      if(array[i] == null){
        return i;
      }
    }
    return array.length;
  }

  public boolean isEmpty() {
    for (E element: array) {
      if(element != null){
        return false;
      }
    }
    return true;
  }

  public boolean contains(Object o) {
    for (E element : this.array) {
      if (element != null && element.equals(o)) {
        return true;
      }
    }
    return false;
  }

  public boolean add(E o) {
    for (int i = 0; i < this.array.length; i++) {
      if(array[i] == null){
        array[i] = o;
        return true;
      }
    }
    return false;
  }

  public boolean remove(Object o) {
    for (int i = 0; i < this.array.length; i++) {
      if(array[i] != null && array[i].equals(o)){
        array[i] = null;
        fillAbsentElements(this.array);
        return true;
      }
    }
    return false;
  }

  public void clear() {
    for (int i = 0; i < this.array.length; i++) {
      array[i] = null;
    }
  }

  private void fillAbsentElements(E[] array){
    List<E> list = Arrays.stream(array)
        .filter(Objects::nonNull)
        .toList();
    for (int i = 0; i < this.array.length; i++) {
      array[i] = null;
    }
    for (int i = 0; i < list.size(); i++) {
      array[i] = list.get(i);
    }
  }

  @Override
  public String toString() {
    return "MyArrayList{" +
        "array=" + Arrays.toString(array) +
        '}';
  }
}
