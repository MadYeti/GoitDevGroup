package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {

  private String jsonInput = "{\"name\": \"Jhon\", \"age\":\"23\"}";

  Calculator calculator;

  @BeforeEach
  public void setUp(){
    System.out.println("invokes before each tests");
    calculator = new Calculator();
  }

  @AfterEach
  public void destroy(){
    System.out.println("invokes after each tests");
  }

  @Test
  void shouldCorrectlySumNumbers(){
    //given
    int firstOperand = 4;
    int secondOperand = 2;

    //when
    int result = calculator.sum(firstOperand, secondOperand);

    //then
    assertEquals(6, result);
    assertThat(result).isEqualTo(6);
  }

  @Test
  void shouldThrowRuntimeExceptionWhenDividerIsZero(){
    //given
    int firstOperand = 4;
    int secondOperand = 0;

    //when
    //then
    assertThrows(IllegalArgumentException.class, () -> calculator.div(firstOperand, secondOperand));
  }

}
