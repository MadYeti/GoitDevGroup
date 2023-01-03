package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ArrayList <E>{

  private E[] array;

  public ArrayList(E[] array){
    this.array = array;
  }

  public boolean add(E o) {
    for(int i = 0; i < array.length; i++){
      if(array[i] == null && o != null){
        array[i] = o;
        return true;
      }
    }
    return false;
  }

  public boolean remove(Object o) {
    boolean flag = false;
    for(int i = 0; i < array.length; i++){
      if(array[i] != null && array[i].equals(o)){
        array[i] = null;
        flag = true;
      }
    }
    fillAbsentElements(this.array);
    return flag;
  }

  public E[] getArray() {
    return array;
  }

  public void setArray(E[] array) {
    this.array = array;
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
}
