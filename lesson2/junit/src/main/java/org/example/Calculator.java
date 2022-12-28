package org.example;

import lombok.Data;

@Data
public class Calculator {

  public int sum(int a, int b){
    return a + b;
  }

  public int sub(int a, int b){
    return a - b;
  }

  public int mul(int a, int b){
    return a * b;
  }

  public int div(int a, int b){
    if(b == 0){
      throw new IllegalArgumentException();
    }
    return a / b;
  }
}
